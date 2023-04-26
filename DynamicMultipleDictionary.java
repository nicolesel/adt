package com.example.base.uade;

public class DynamicMultipleDictionary implements IMultipleDictionary {

    private MultipleDictionaryNode first;
    private int size;

    public DynamicMultipleDictionary() {
        size = 0;
    }

    @Override
    public void add(int key, int value) {
        // TODO
    }

    @Override
    public void remove(int key, int value) {
        // TODO
    }

    @Override
    public ISet getKeys() {
        ISet keySet = new StaticSet();
        MultipleDictionaryNode candidate = this.first;
        while(candidate != null) {
            keySet.add(candidate.getKey());
            if(candidate.getNext() != null) {
                candidate = candidate.getNext();
            }
        }
        return keySet;
    }

    @Override
    public ISet getValues(int key) {
        MultipleDictionaryNode candidate = this.first;
        while(candidate != null) {
            if(candidate.getKey() == key) {
                return candidate.getValue();
            }

            if(candidate.getNext() != null) {
                candidate = candidate.getNext();
            }
        }
        return null; // Error
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private MultipleDictionaryNode indexOfKey(int key) {
        if(this.first == null) {
            return null;
        }
        MultipleDictionaryNode candidate = this.first;
        while(candidate.getNext() != null) {
            if(candidate.getKey() == key) {
                return candidate;
            }
            candidate = candidate.getNext();
        }
        return null;
    }
}
