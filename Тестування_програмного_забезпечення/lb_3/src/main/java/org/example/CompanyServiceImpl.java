package org.example;

/*
  @author   Anna Melnychuk
  @project   lb_3
  @class  group 444A
  @version  1.0.0
  @since 28.09.24 - 15:35
*/

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyServiceImpl implements ICompanyService {

    @Override
    public Company getTopLevelParent(Company child) {
        Company result = child;
        if(child.getParent() != null ){
            result = this.getTopLevelParent(child.getParent());
        }
        return result;
    }

    private Set<Company> getAllChildrenAndCompanyToSet (Company company, List<Company> companies){
        Set<Company> children = new HashSet<>();
        Set<Company> set = new HashSet<>();
        for(Company cmp:companies){
            while (true){
                set.add(cmp);
                if ( cmp.getParent() == null || cmp.equals(company)){
                    break;
                }
                cmp=cmp.getParent();
            }
            if (set.contains(company)) {
                children.addAll(set);
            }
            set.clear();
        }
        return children;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        long result = 0;

        // Додаємо працівників самої компанії
        result += company.getEmployeesCount();

        // Перевіряємо, чи є у компанії батьківська компанія
        Company parent = company.getParent();
        while (parent != null) {
            result += parent.getEmployeesCount(); // Додаємо працівників батьківської компанії
            parent = parent.getParent(); // Переходимо до батьківської компанії
        }

        return result;
    }

    @Override
    public long getEmployeeCountForChildrenAndCompany(Company company, List<Company> companies) {
        Set<Company> children = this.getAllChildrenAndCompanyToSet(company, companies);
        long result = 0;
        for (Company cmp:children) {
            result+=cmp.getEmployeesCount();
        }

        return result;
    }



}
