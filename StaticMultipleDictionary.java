package com.example.base.uade;

import java.util.Arrays;

public class StaticMultipleDictionary implements IMultipleDictionary {
    private int[] keys;
    private ISet[] values;
    private int size;

    public StaticMultipleDictionary() {
        this.keys = new int[10000];
        this.values = new StaticSet[10000];
        this.size = 0;
    }

    @Override
    public void add(int key, int value) {
        int index = indexOfKey(key);
        if (index != -1) {
            StaticSet staticSet = new StaticSet();
            staticSet.add(value);
            this.values[index] = staticSet;
            return;
        }
        if (this.size == this.keys.length) {
            // Si el array de keys está lleno, se duplica su tamaño
            this.keys = Arrays.copyOf(this.keys, this.keys.length * 2);
            this.values = Arrays.copyOf(this.values, this.values.length * 2);
        }
        this.keys[this.size] = key;
        this.values[this.size].add(value);
        this.size++;
    }

    @Override
    public void remove(int key, int value) { // TODO no debe eliminar el conjunto, sino un elemento del conjunto
        int index = indexOfKey(key);
        if (index != -1 && this.existsValue(this.values[index], value)) {
            for (int i = index; i < this.size - 1; i++) {
                this.keys[i] = this.keys[i + 1];
                this.values[i] = this.values[i + 1];
            }
            this.size--;
        }
    }

    private boolean existsValue(ISet set, int value) {
        ISet copy = new StaticSet();
        boolean exists = false;
        while(!set.isEmpty()) {
            int element = set.choose();
            if(element == value) {
                exists = true;
                break;
            }
            copy.add(element);
            set.choose();
        }
        while(!copy.isEmpty()) {
            int element = copy.choose();
            set.add(element);
            copy.choose();
        }
        return exists;
    }

    @Override
    public ISet getKeys() {
        ISet keySet = new StaticSet();
        for (int i = 0; i < this.size; i++) {
            keySet.add(this.keys[i]);
        }
        return keySet;
    }

    @Override
    public ISet getValues(int key) {
        int index = indexOfKey(key);
        if (index != -1) {
            return this.values[index];
        }
        return null; // Error
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int indexOfKey(int key) {
        for (int i = 0; i < this.size; i++) {
            if (this.keys[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
