package com.lld.cache.algorithms;

public class DoublyLinkedList<E> {
    private Node<E> head, tail;
    public DoublyLinkedList() {
        head = tail = null;
    }

    public Node<E> getHead() {
        return this.head;
    }

    public E getHeadElement() {
        return this.head.item;
    }

    public void add(final E item) {
        Node<E> a = createNode(item);
        if(isEmpty()) {
            head = tail = a;
            return;
        }
        tail.next = a;
        a.prev = tail;
        tail = tail.next;
    }

    public void remove(final Node<E> node) {
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;
        if(prevNode == null) {
            head = head.next;
            nextNode.prev = null;
            return;
        }
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private Node<E> createNode(final E item) {
        final Node<E> a = new Node<>();
        a.prev = null;
        a.next = null;
        a.item = item;
        return a;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;
    }
}
