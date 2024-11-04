package com.keyin;

abstract class Animal {
    private final String name;
    private final long timestamp; // Stores arrival time as a timestamp

    public Animal(String name) {
        this.name = name;
        this.timestamp = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Check if this animal arrived before another animal
    public boolean isOlderThan(Animal other) {
        return this.timestamp < other.getTimestamp();
    }
}