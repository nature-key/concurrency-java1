//package com.jiepi.concurrency.HashMap;
//
//import javax.swing.tree.TreeNode;
//import java.io.Serializable;
//import java.util.AbstractMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable {
//    // 序列号
//    private static final long serialVersionUID = 362498820763181265L;
//    // 默认的初始容量是16
//    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
//    // 最大容量
//    static final int MAXIMUM_CAPACITY = 1 << 30;
//    // 默认的填充因子
//    static final float DEFAULT_LOAD_FACTOR = 0.75f;
//    // 当桶(bucket)上的结点数大于这个值时会转成红黑树
//    static final int TREEIFY_THRESHOLD = 8;
//    // 当桶(bucket)上的结点数小于这个值时树转链表
//    static final int UNTREEIFY_THRESHOLD = 6;
//    // 桶中结构转化为红黑树对应的table的最小大小
//    static final int MIN_TREEIFY_CAPACITY = 64;
//    // 存储元素的数组，总是2的幂次倍
//    transient Node<k,v>[] table;
//    // 存放具体元素的集
//    transient Set<map.entry<k,v>> entrySet;
//    // 存放元素的个数，注意这个不等于数组的长度。
//    transient int size;
//    // 每次扩容和更改map结构的计数器
//    transient int modCount;
//    // 临界值 当实际大小(容量*填充因子)超过临界值时，会进行扩容
//    int threshold;
//    // 加载因子
//    final float loadFactor;
//
//
//
//
//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;// 哈希值，存放元素到hashmap中时用来与其他元素hash值比较
//        final K key;//键
//        V value;//值
//        // 指向下一个节点
//        Node<K,V> next;
//        Node(int hash, K key, V value, Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//        public final K getKey()        { return key; }
//        public final V getValue()      { return value; }
//        public final String toString() { return key + "=" + value; }
//        // 重写hashCode()方法
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//        // 重写 equals() 方法
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//            if (o instanceof Map.Entry) {
//                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
//                if (Objects.equals(key, e.getKey()) &&
//                        Objects.equals(value, e.getValue()))
//                    return true;
//            }
//            return false;
//        }
//    }
//
//
//
//    // 默认构造函数。
//    public HashMap() {
//        this.loadFactor = DEFAULT_LOAD_FACTOR; // all   other fields defaulted
//    }
//
//    // 包含另一个“Map”的构造函数
//    public HashMap(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);//
//    }
//
//    // 指定“容量大小”的构造函数
//    public HashMap(int initialCapacity) {
//        this(initialCapacity, DEFAULT_LOAD_FACTOR);
//    }
//
//    // 指定“容量大小”和“加载因子”的构造函数
//    public HashMap(int initialCapacity, float loadFactor) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
//        if (initialCapacity > MAXIMUM_CAPACITY)
//            initialCapacity = MAXIMUM_CAPACITY;
//        if (loadFactor <= 0 || Float.isNaN(loadFactor))
//            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
//        this.loadFactor = loadFactor;
//        this.threshold = tableSizeFor(initialCapacity);
//    }
//
//
//
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        // table未初始化或者长度为0，进行扩容
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        // (n - 1) & hash 确定元素存放在哪个桶中，桶为空，新生成结点放入桶中(此时，这个结点是放在数组中)
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);
//            // 桶中已经存在元素
//        else {
//            Node<K,V> e; K k;
//            // 比较桶中第一个元素(数组中的结点)的hash值相等，key相等
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                // 将第一个元素赋值给e，用e来记录
//                e = p;
//                // hash值不相等，即key不相等；为红黑树结点
//            else if (p instanceof TreeNode)
//                // 放入树中
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//                // 为链表结点
//            else {
//                // 在链表最末插入结点
//                for (int binCount = 0; ; ++binCount) {
//                    // 到达链表的尾部
//                    if ((e = p.next) == null) {
//                        // 在尾部插入新结点
//                        p.next = newNode(hash, key, value, null);
//                        // 结点数量达到阈值，转化为红黑树
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        break;// 跳出循环
//                    }
//                    // 判断链表中结点的key值与插入的元素的key值是否相等
//                    if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k))))
//                        break; // 相等，跳出循环
//                    // 用于遍历桶中的链表，与前面的e = p.next组合，可以遍历链表
//                    p = e;
//                }
//            }// 表示在桶中找到key值、hash值与插入元素相等的结点
//            if (e != null) {
//                // 记录e的value
//                V oldValue = e.value;
//                // onlyIfAbsent为false或者旧值为null
//                if (!onlyIfAbsent || oldValue == null)
//                    //用新值替换旧值
//                    e.value = value;
//                // 访问后回调
//                afterNodeAccess(e);
//                // 返回旧值
//                return oldValue;
//            }
//        }
//        ++modCount;
//        // 实际大小大于阈值则扩容
//        if (++size > threshold)
//            resize();
//        // 插入后回调
//        afterNodeInsertion(evict);
//        return null;
//    }
//
//
//
//
//
//
//    public V get(Object key) {
//        Node<K,V> e;
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }
//
//    final Node<K,V> getNode(int hash, Object key) {
//        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&//数组不为空且第一个节点不为空
//                (first = tab[(n - 1) & hash]) != null) {
//            // 如果第一个节点的hash相等 key相等  则直接返回第一个节点
//            if (first.hash == hash && // always check first node
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            // 桶中不止一个节点
//            if ((e = first.next) != null) {
//                // 在树中get
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
//                // 在链表中get
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }
//
//
//
//
//
//
//    final Node<K,V>[] resize() {
//        Node<K,V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            // 超过最大值就不再扩充了，就只好随你碰撞去吧
//            if (oldCap >= MAXIMUM_CAPACITY) {
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            }
//            // 没超过最大值，就扩充为原来的2倍
//            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY)
//                newThr = oldThr << 1; // double threshold
//        }
//        else if (oldThr > 0) // initial capacity was placed in threshold
//            newCap = oldThr;
//        else {
//            // signifies using defaults
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
//        // 计算新的resize上限
//        if (newThr == 0) {
//            float ft = (float)newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes","unchecked"})
//        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
//        table = newTab;
//        if (oldTab != null) {
//            // 把每个bucket都移动到新的buckets中
//            for (int j = 0; j < oldCap; ++j) {
//                Node<K,V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof TreeNode)
//                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
//                    else {
//                        Node<K,V> loHead = null, loTail = null;
//                        Node<K,V> hiHead = null, hiTail = null;
//                        Node<K,V> next;
//                        do {
//                            next = e.next;
//                            // 原索引
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            }
//                            // 原索引+oldCap
//                            else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        // 原索引放到bucket里
//                        if (loTail != null) {
//                            loTail.next = null;
////                        ÷    newTab[j] = loHead;
//                        }
//                        // 原索引+oldCap放到bucket里
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }
//
//
//
//    static final int hash(Object key) {
//        int h;
//        // key.hashCode()：返回散列值也就是hashcode
//        // ^ ：按位异或
//        // >>>:无符号右移，忽略符号位，空位都以0补齐
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }å÷？å
//
//
//    static int hash(int h) {
//        // This function ensures that hashCodes that differ only by
//        // constant multiples at each bit position have a bounded
//        // number of collisions (approximately 8 at default load factor).
//
//        h ^= (h >>> 20) ^ (h >>> 12);
//        return h ^ (h >>> 7) ^ (h >>> 4);
//    }
//
//
//
//
//}
