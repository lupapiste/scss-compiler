(ns scss-compiler.core
  (:require [clojure.java.io :as io]
            [clojure.walk :as walk]
            [zweikopf.core :as z]
            [camel-snake-kebab :as csk]))


;; Initialization

(def compiler-config
  (atom {}))

(def ^:private compass-load-path
  (delay
    (-> "Compass.sass_engine_options[:load_paths].select { |p| p.respond_to? :root }.map(&:root)"
      z/ruby-eval
      z/clojurize)))

(def init-once
  (delay
    (z/init-ruby-context)

    (z/set-gem-home (:gempath @compiler-config))
    (z/set-gem-path (:gempath @compiler-config))
  
    (z/ruby-require "sass")
    (z/ruby-require "compass")

    (swap! compiler-config assoc :default-sass-opts {:load_paths @compass-load-path :style :compact})))

(defn initialize [& {:as config}]
  (swap! compiler-config merge @compiler-config config))


;; Compiler

(defn- snake-case-key [[k v]] [(csk/->snake_case k) v])

(defn- underscore-keys [m]
  (walk/postwalk (fn [x] (if (map? x) (into {} (map snake-case-key x)) x)) m))

(defn- create-engine [fpath options]
  (let [rubyized-opts (z/rubyize (underscore-keys options))]
    (z/call-ruby "Sass::Engine" :for_file fpath rubyized-opts)))

(defn scss->css [fpath & {:as options}]
  @init-once
  (let [engine (create-engine fpath (merge (:default-sass-opts @compiler-config) options))]
    (z/call-ruby engine :render)))

