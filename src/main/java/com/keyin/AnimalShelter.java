package com.keyin;
import java.util.LinkedList;
import java.util.Queue;


public class AnimalShelter {
    private final Queue<Dog> dogs;
    private final Queue<Cat> cats;

    public AnimalShelter() {
        dogs = new LinkedList<>();
        cats = new LinkedList<>();
    }

    // Enqueue a new animal with the current timestamp
    public void enqueue(Animal animal) {
        if (animal instanceof Dog) {
            dogs.add((Dog) animal);
        } else if (animal instanceof Cat) {
            cats.add((Cat) animal);
        }
    }

    // Dequeue the oldest animal, regardless of type
    public Animal dequeueAny() {
        if (dogs.isEmpty() && cats.isEmpty()) {
            return null;
        } else if (dogs.isEmpty()) {
            return dequeueCat();
        } else if (cats.isEmpty()) {
            return dequeueDog();
        }

        Dog oldestDog = dogs.peek();
        Cat oldestCat = cats.peek();

        if (oldestDog.isOlderThan(oldestCat)) {
            return dogs.poll();
        } else {
            return cats.poll();
        }
    }

    // Dequeue the oldest dog
    public Dog dequeueDog() {
        return dogs.poll();
    }

    // Dequeue the oldest cat
    public Cat dequeueCat() {
        return cats.poll();
    }

    // Check if the shelter is empty
    public boolean isEmpty() {
        return dogs.isEmpty() && cats.isEmpty();
    }
}