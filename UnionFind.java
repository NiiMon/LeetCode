class UnionFind {
    private final int _size;
    private int[] _parent;
    private int[] _rank;
    private int _groups;
    
    public UnionFind(int size) {
        _size = size;
        _parent = new int[size];
        _rank = new int[size];
        for (int i = 0; i < size; i++) {
            _parent[i] = i;
            _rank[i] = 1;
        }
        _groups = size;
    }
    
    public int find(int p) {
        // find parent
        int root = _parent[p];
        while (_parent[root] != root) {
            root = _parent[root];
        }
        // path compression
        while (_parent[p] != root) {
            int next = _parent[p];
            _parent[p] = root;
            p = next;
        }
        
        return root;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int getSize() {
        return _size;
    }

    public int getGroups() {
        return _groups;
    }
    
    public void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }
        
        int root1 = find(p);
        int root2 = find(q);
        
        if (_rank[root1] > _rank[root2]) {
            _parent[root2] = root1;
        } else if (_rank[root1] < _rank[root2]) {
            _parent[root1] = root2;
        } else {
            _parent[root2] = root1;
            _rank[root1]++;
        }
        
        _groups--;
    }
}

