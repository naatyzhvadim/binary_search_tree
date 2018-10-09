import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TreeMap<K extends Comparable<K>, V> implements java.util.Map<K, V>{
    private BinaryTree<K, V> binaryTree = new BinaryTree<>();
    @Override
    public int size() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean isEmpty() {
        return binaryTree == null;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key == null){
            return false;
        }
        try{
            @SuppressWarnings("unchecked") K castedKey = (K) key;
            return binaryTree.find(castedKey) != null;
        }catch (ClassCastException e){
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        if (key == null){
            return null;
        }
        try {
            @SuppressWarnings("unchecked") K castedKey = (K) key;
            return binaryTree.find(castedKey);
        } catch (ClassCastException e){
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        return binaryTree.changeNode(key, value);
    }

    @Override
    public V remove(Object key) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }
    public static void main(String[] args){
        TreeMap<Integer, Double> treeMap = new TreeMap<>();
        treeMap.put(5, 5.5);
        System.out.println(treeMap.get(5));
        treeMap.put(5, 5.6);
        System.out.println(treeMap.get(5));

    }
}
