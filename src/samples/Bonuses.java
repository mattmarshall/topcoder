package samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Bonuses {
    
    private static class PointAllocation {
        final int index;
        final int points;
        int percentage;
        
        public PointAllocation(final int index, final int points) {
            this.index = index;
            this.points = points;
        }

        @Override
        public int hashCode() {
            return index;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final PointAllocation other = (PointAllocation) obj;
            return this.index == other.index;
        }        
        
    }
    
    public int[] getDivision(int[] points) {
        
        final PointAllocation[] pa = new PointAllocation[points.length];
        SortedMap<Integer, List<PointAllocation>> pointsToAllocations = new TreeMap<>(Collections.reverseOrder());

        // Add point objects
        double total = 0;
        for (int i = 0; i < points.length; i++) {
            
            PointAllocation p = new PointAllocation(i, points[i]);
            total += p.points;
            pa[i] = p;
            
            List<PointAllocation> l = null;
            if (pointsToAllocations.containsKey(p.points)) {
                l = pointsToAllocations.get(p.points);
            } else {
                l = new ArrayList<>();
                pointsToAllocations.put(p.points, l);
            }
            
            // Add point
            l.add(p);
        }
        
        // Determine percentages
        int allocated = 0;
        for (int i = 0; i < pa.length; i++) {
            int percent = (int) Math.floor(points[i] / total * 100);
            pa[i].percentage = percent;
            allocated += percent;
        }
        
        // Total allocated
        int difference = 100 - allocated;
        System.out.println("Total allocated: " + allocated);
        System.out.println("Difference: " + difference);

        // Iterate over percentages, add back to employees allocations
        Iterator<PointAllocation> pts = new PointAllocationIterator(pointsToAllocations);
        for (int i = 0; i < difference; i++) {
            if (pts.hasNext()) {
                PointAllocation p = pts.next();
                p.percentage++;
            }
        }

        // Create return
        int[] bonuses = new int[pa.length];
        for (int i = 0; i < pa.length; i++) {
            bonuses[i] = pa[i].percentage;
        }

        return bonuses;
    }
    
    private static class PointAllocationIterator implements Iterator<PointAllocation> {
        
        private final Iterator<List<PointAllocation>> lstIt;
        private Iterator<PointAllocation> paIt;
        
        public PointAllocationIterator(Map<Integer, List<PointAllocation>> map) {
            this.lstIt = map.values().iterator();
        }

        @Override
        public boolean hasNext() {
            if (paIt == null) {
                return lstIt.hasNext();
            } else {
                return paIt.hasNext() || lstIt.hasNext();
            }
        }

        @Override
        public PointAllocation next() {
            if (paIt == null || !paIt.hasNext()) {
                paIt = lstIt.next().iterator();
            }
            return paIt.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }

    public static void main(String[] args) {
        printDivision(new int[]{1, 2, 3, 4, 5});
        printDivision(new int[]{5, 5, 5, 5, 5, 5});
        printDivision(new int[]{485, 324, 263, 143, 470, 292, 304, 188, 100, 254, 296, 255, 360, 231, 311, 275, 93, 463, 115, 366, 197, 470});
    }

    private static void printDivision(int[] points) {
        Bonuses b = new Bonuses();
        int[] val = b.getDivision(points);
        StringBuilder sb = new StringBuilder("{");
        for (int i : val) {
            sb.append(" ");
            sb.append(String.valueOf(i));
            sb.append(", ");
        }
        sb.append("}");
        System.out.println(sb.toString());
    }

}
