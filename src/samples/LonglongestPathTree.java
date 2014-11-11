package samples;

public class LonglongestPathTree {

    private static class Tree implements Comparable<Tree> {

        private final long root;
        private final long vertex1;
        private final long vertex2;
        private final long edge1;
        private final long edge2;

        public Tree(long root, long vertex1, long vertex2, long edge1, long edge2) {
            this.root = root;
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.edge1 = edge1;
            this.edge2 = edge2;
        }

        public long getDiameter() {
            return edge1 + edge2;
        }

        @Override
        public int compareTo(Tree tree) {
            if (tree == null) {
                return Integer.MAX_VALUE;
            }
            return Integer.compare((int) getDiameter(), (int) tree.getDiameter());
        }

        @Override
        public String toString() {
            return "Tree{" + "root=" + root + ", vertex1=" + vertex1 + ", vertex2=" + vertex2 + ", edge1=" + edge1 + ", edge2=" + edge2 + '}';
        }
    }

    public long getLength(int[] A, int[] B, int[] L) {
        assert A != null && B != null && L != null && (A.length == B.length && B.length == L.length);
        return 1;
    }

}
