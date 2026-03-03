package com.lld.cache.evictionpolicy;

import com.lld.cache.algorithms.DoublyLinkedList;
import org.jetbrains.annotations.NotNull;

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
    public void keyAccessed(@NotNull final T key) {
        if(!referenceMap.containsKey(key)) {
            final DoublyLinkedList.Node<T> keyReference = dll.add(key);
            referenceMap.put(key, keyReference);
            return;
        }
        final DoublyLinkedList.Node<T> oldKeyReference = referenceMap.get(key);
        dll.remove(oldKeyReference);
        final DoublyLinkedList.Node<T> newKeyReference = dll.add(key);
        referenceMap.put(key, newKeyReference);
    }
}
