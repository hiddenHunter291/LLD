package com.lld.cache.evictionpolicy;

import com.lld.cache.algorithms.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<T> implements EvictionPolicy<T> {
    private final DoublyLinkedList<T> dll;
    private final Map<T, DoublyLinkedList.Node<T>> referenceMap;

    public LRUEvictionPolicy() {
        dll = new DoublyLinkedList<>();
        referenceMap = new HashMap<>();
    }

    @Override
    public T evict() {
        final DoublyLinkedList.Node<T> node = dll.getHead();
        final T element = dll.getHeadElement();
        dll.remove(node);
        referenceMap.remove(element);
        return element;
    }

    @Override
    public void keyAccessed(final T key) {
        if(!referenceMap.containsKey(key)) {
           final DoublyLinkedList.Node<T> keyReference = new DoublyLinkedList.Node<>();
           referenceMap.put(key, keyReference);
           dll.add(key);
           return;
        }
        final DoublyLinkedList.Node<T> keyReference = referenceMap.get(key);
        dll.remove(keyReference);
        dll.add(key);
    }
}
