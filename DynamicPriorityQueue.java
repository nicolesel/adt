package com.example.base.uade;

public class DynamicPriorityQueue implements IPriorityQueue {

    private PriorityNode first;

    @Override
    public void add(int a, int priority) {
        if(this.first == null) {
            this.first = new PriorityNode(a, priority,null);
            return;
        }
        PriorityNode candidate = this.first;
        while(candidate.getNext() != null && candidate.getPriority() > priority) {
            candidate = candidate.getNext();
        }
        candidate.setNext(new PriorityNode(a, priority,candidate.getNext()));
    }

    @Override
    public void remove() {
        if(this.first == null) {
            System.out.println("No se puede desacolar una cola vacia");
            return;
        }
        this.first = this.first.getNext();
    }

    @Override
    public boolean isEmpty() {
        return this.first == null;
    }

    @Override
    public int getFirst() {
        if(this.first == null) {
            System.out.println("No se puede obtener el primero una cola vacia");
            return -1;
        }
        return this.first.getValue();
    }

    @Override
    public int getPriority() {
        if(this.first == null) {
            System.out.println("No se puede obtener la prioridad del primero una cola vacia");
            return -1;
        }
        return this.first.getPriority();
    }
}
