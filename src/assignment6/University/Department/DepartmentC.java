package assignment6.University.Department;

import java.util.Comparator;
import java.util.LinkedList;

public class DepartmentC extends LinkedList<Department> {
    @Override
    public boolean add(Department department) {
        if (!super.contains(department)) {
            super.add(department);
            super.sort(Comparator.naturalOrder());
            return true;
        }
        return false;
    }
}
