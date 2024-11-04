package com.keyin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterTest {
    private AnimalShelter shelter;

    @BeforeEach
    void setUp() {
        shelter = new AnimalShelter();
    }

    @Test
    void testEnqueueAndDequeueAny() throws InterruptedException {
        Dog dog1 = new Dog("Dog1");
        Thread.sleep(10); // Ensuring unique timestamps
        Cat cat1 = new Cat("Cat1");
        Thread.sleep(10);
        Dog dog2 = new Dog("Dog2");

        shelter.enqueue(dog1);
        shelter.enqueue(cat1);
        shelter.enqueue(dog2);

        // The oldest animal (dog1) should be dequeued first
        assertEquals("Dog1", shelter.dequeueAny().getName());

        // Next, the oldest animal should be cat1
        assertEquals("Cat1", shelter.dequeueAny().getName());

        // Finally, dog2 should be dequeued
        assertEquals("Dog2", shelter.dequeueAny().getName());

        // The shelter should now be empty
        assertTrue(shelter.isEmpty());
    }

    @Test
    void testDequeueDog() throws InterruptedException {
        Dog dog1 = new Dog("Dog1");
        Thread.sleep(10);
        Dog dog2 = new Dog("Dog2");
        Thread.sleep(10);
        Cat cat1 = new Cat("Cat1");

        shelter.enqueue(dog1);
        shelter.enqueue(dog2);
        shelter.enqueue(cat1);

        // Dequeue the oldest dog, which should be dog1
        assertEquals("Dog1", shelter.dequeueDog().getName());

        // The next oldest dog should be dog2
        assertEquals("Dog2", shelter.dequeueDog().getName());

        // No dogs left, so dequeueDog should return null
        assertNull(shelter.dequeueDog());
    }

    @Test
    void testDequeueCat() throws InterruptedException {
        Cat cat1 = new Cat("Cat1");
        Thread.sleep(10);
        Dog dog1 = new Dog("Dog1");
        Thread.sleep(10);
        Cat cat2 = new Cat("Cat2");

        shelter.enqueue(cat1);
        shelter.enqueue(dog1);
        shelter.enqueue(cat2);

        // Dequeue the oldest cat, which should be cat1
        assertEquals("Cat1", shelter.dequeueCat().getName());

        // The next oldest cat should be cat2
        assertEquals("Cat2", shelter.dequeueCat().getName());

        // No cats left, so dequeueCat should return null
        assertNull(shelter.dequeueCat());
    }

    @Test
    void testEmptyShelter() {
        // Test that dequeue operations on an empty shelter return null
        assertNull(shelter.dequeueAny());
        assertNull(shelter.dequeueDog());
        assertNull(shelter.dequeueCat());
        assertTrue(shelter.isEmpty());
    }

    @Test
    void testMixedDequeue() throws InterruptedException {
        Dog dog1 = new Dog("Dog1");
        Thread.sleep(10);
        Cat cat1 = new Cat("Cat1");
        Thread.sleep(10);
        Dog dog2 = new Dog("Dog2");
        Thread.sleep(10);
        Cat cat2 = new Cat("Cat2");

        shelter.enqueue(dog1);
        shelter.enqueue(cat1);
        shelter.enqueue(dog2);
        shelter.enqueue(cat2);

        // Dequeue the oldest animal, should return dog1
        assertEquals("Dog1", shelter.dequeueAny().getName());

        // Dequeue the oldest cat, should return cat1
        assertEquals("Cat1", shelter.dequeueCat().getName());

        // Dequeue the oldest dog, should return dog2
        assertEquals("Dog2", shelter.dequeueDog().getName());

        // Dequeue any, should return the last remaining cat, cat2
        assertEquals("Cat2", shelter.dequeueAny().getName());

        // Shelter should now be empty
        assertTrue(shelter.isEmpty());
    }
}

