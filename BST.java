
package photoapp;
public class BST<T> {
    private class BSTNode<T> {
        String key;
        T data;
        BSTNode<T> left, right;

        public BSTNode(String k, T val) {
            key = k;
            data = val;
            left = right = null;
        }
    }

    private BSTNode<T> root, current;
    private String allKeys;

    public BST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public T retrieve() {
        return current.data;
    }

    public boolean findkey(String tkey) {
        BSTNode<T> p = root;
        BSTNode<T> q = root;

        if (empty())
            return false;

        while (p != null) {
            q = p;
            if (p.key.compareToIgnoreCase(tkey) == 0) {
                current = p;
                return true;
            } else if (tkey.compareToIgnoreCase(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        current = q;
        return false;
    }

    public boolean insert(String k, T val) {
        if (findkey(k)) {
            return false;
        }
        BSTNode<T> p = new BSTNode<T>(k, val);
        if (empty()) {
            root = current = p;
        } else {
            if (k.compareToIgnoreCase(current.key) < 0) {
                current.left = p;
            } else {
                current.right = p;
            }
            current = p;
        }
        return true;
    }

    public boolean update(String k, T val) {
        removeKey(current.key);
        return insert(k, val);
    }

    public boolean removeKey(String k) {
        root = remove_aux(k, root);
        current = root;
        return true;
    }

    private BSTNode<T> remove_aux(String k, BSTNode<T> p) {
        if (p == null)
            return null;
        if (k.compareToIgnoreCase(p.key) < 0) {
            p.left = remove_aux(k, p.left);
        } else if (k.compareToIgnoreCase(p.key) > 0) {
            p.right = remove_aux(k, p.right);
        } else {
            if (p.left != null && p.right != null) {
                BSTNode<T> min = find_min(p.right);
                p.key = min.key;
                p.data = min.data;
                p.right = remove_aux(min.key, p.right);
            } else {
                p = (p.left != null) ? p.left : p.right;
            }
        }
        return p;
    }

    private BSTNode<T> find_min(BSTNode<T> p) {
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public String inOrder() {
        allKeys = "";
        if (root != null) {
            inorder(root);
        }
        return allKeys;
    }

    private void inorder(BSTNode<T> p) {
        if (p.left != null)
            inorder(p.left);
        if (allKeys.isEmpty())
            allKeys = p.key;
        else
            allKeys += " AND " + p.key;
        if (p.right != null)
            inorder(p.right);
    }
}
