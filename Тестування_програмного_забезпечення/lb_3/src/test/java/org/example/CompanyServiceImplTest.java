package org.example;
import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
/*
  @author   Anna Melnychuk
  @project   lb_3
  @class  group 444A
  @version  1.0.0
  @since 28.09.24 - 15:35
*/
class CompanyServiceImplTest {

    // Створила ієрархію компаній
    /* Далі в мене є три методи :
    *  1 метод - getTopLevelParent - метод який повертає головну компанію (10 тестів)
    *
    *  2 метод - getEmployeeCountForCompanyAndChildren - метод який визначає кількість працівників
    *  рухаючись вверх по ієрархії , до прикладу від front-end -> development -> main (8 тестів)
    *
    *  3 метод - getEmployeeCountForChildrenAndCompany - метод який визначає кількість працівників
    *    у дочірніх компаніях , до прикладу від front-end до її дочірніх - ui та ux  (5 тестів)*/
    private final Company main = new Company(null, 3);
    private final Company bookkeeping = new Company(main, 2);
    private final Company development = new Company(main, 4);
    private final Company frontEnd = new Company(development, 11);
    private final Company backEnd = new Company(development, 10);
    private final Company design = new Company(development, 3);
    private final Company businessLogic = new Company(backEnd, 5);
    private final Company database = new Company(backEnd, 3);
    private final Company uiTeam = new Company(frontEnd, 6);
    private final Company uxTeam = new Company(frontEnd, 4);

    List<Company> companies = List.of(
            main, bookkeeping, development, frontEnd, backEnd, design, businessLogic, database, uiTeam, uxTeam
    );

    private final ICompanyService underTest = new CompanyServiceImpl();

    // Тести для 1 методу getTopLevelParent
    @Test
    void whenParentIsNullThenItIsTheTop() {
        // Перевіряє, чи метод повертає компанію як головну, якщо у неї немає батьків
        Company result = underTest.getTopLevelParent(main);
        assertEquals(main, result);
    }

    @Test
    void whenDesignIsQueriedThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для компанії design
        Company result = underTest.getTopLevelParent(design);
        assertEquals(main, result);
    }

    @Test
    void whenCompanyHasMultipleChildrenThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для дочірніх компаній
        Company result1 = underTest.getTopLevelParent(frontEnd);
        Company result2 = underTest.getTopLevelParent(backEnd);
        assertEquals(main, result1);
        assertEquals(main, result2);
    }

    @Test
    void whenCompanyIsLeafThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для компанії, яка є дочірньою, але не має власних дочірніх компаній
        Company result = underTest.getTopLevelParent(bookkeeping);
        assertEquals(main, result);
    }

    @Test
    void whenDatabaseIsQueriedThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для компанії database
        Company result = underTest.getTopLevelParent(database);
        assertEquals(main, result);
    }

    @Test
    void whenCompanyHasNestedChildrenThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для компанії uiTeam, яка має вкладених дітей
        Company result = underTest.getTopLevelParent(uiTeam);
        assertEquals(main, result);
    }

    @Test
    void whenCompanyHasThreeLevelsOfHierarchyThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для компанії businessLogic, яка має три рівні ієрархії
        Company result = underTest.getTopLevelParent(businessLogic);
        assertEquals(main, result);
    }

    @Test
    void whenCompanyHasNoChildrenThenReturnSelf() {
        // Перевіряє, чи метод повертає саму компанію, якщо у неї немає дочірніх компаній
        Company isolatedCompany = new Company(null, 0);
        Company result = underTest.getTopLevelParent(isolatedCompany);
        assertEquals(isolatedCompany, result);
    }

    @Test
    void whenSiblingsHaveSameAttributesThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для двох братів з однаковими атрибутами
        Company sibling1 = new Company(main, 5);
        Company sibling2 = new Company(main, 5);
        assertEquals(main, underTest.getTopLevelParent(sibling1));
        assertEquals(main, underTest.getTopLevelParent(sibling2));
    }

    @Test
    void whenCompanyIsAtDifferentLevelsThenReturnTopParent() {
        // Перевіряє, чи метод повертає головну компанію для компаній, які знаходяться на різних рівнях
        Company result1 = underTest.getTopLevelParent(frontEnd);
        Company result2 = underTest.getTopLevelParent(businessLogic);
        assertEquals(main, result1);
        assertEquals(main, result2);
    }

    // Тести для методу getEmployeeCountForCompanyAndChildren
    // Тут у нас йде підйом
    // Тобто, до прикладу , від front-end -> development -> main
    @Test
    void whenDatabaseIsQueriedThenCountEmployeesUpwardHierarchy() {
        // Перевіряє, чи метод коректно підраховує кількість працівників, починаючи з database
        long employeeCountFromDatabase = underTest.getEmployeeCountForCompanyAndChildren(database, companies);
        assertEquals(20, employeeCountFromDatabase); // 3 (database) + 10 (backEnd) + 4 (development) + 3 (main)
    }

    @Test
    void whenBackEndIsQueriedThenCountEmployeesUpwardHierarchy() {
        // Перевіряє, чи метод коректно підраховує кількість працівників, починаючи з backEnd
        long employeeCountFromBackEnd = underTest.getEmployeeCountForCompanyAndChildren(backEnd, companies);
        assertEquals(17, employeeCountFromBackEnd); // 10 (backEnd) + 4 (development) + 3 (main)
    }

    @Test
    void whenUiTeamIsQueriedThenCountEmployeesUpwardHierarchy() {
        // Перевіряє, чи метод коректно підраховує кількість працівників, починаючи з uiTeam
        long employeeCountFromUiTeam = underTest.getEmployeeCountForCompanyAndChildren(uiTeam, companies);
        assertEquals(24, employeeCountFromUiTeam); // 6 (uiTeam) + 11 (frontEnd) + 4 (development) + 3 (main)
    }

    @Test
    void whenCompanyWithNoChildrenIsQueriedThenReturnOwnEmployeeCount() {
        // Перевіряє, чи метод повертає кількість працівників компанії без дітей
        Company companyWithNoChildren = new Company(null, 0);
        long employeeCount = underTest.getEmployeeCountForCompanyAndChildren(companyWithNoChildren, companies);
        assertEquals(0, employeeCount); // Оскільки у компанії немає працівників
    }

    @Test
    void whenFrontEndIsQueriedThenReturnEmployeeCountIncludingChildren() {
        // Перевіряє, чи метод коректно підраховує кількість працівників, починаючи з frontEnd
        long employeeCount = underTest.getEmployeeCountForCompanyAndChildren(frontEnd, companies);
        assertEquals(18, employeeCount); // 11 (frontEnd) + 4 (development) + 3 (main)
    }

    @Test
    void whenDevelopmentIsQueriedThenCountEmployeesUpwardHierarchy() {
        // Перевіряє, чи метод коректно підраховує кількість працівників, починаючи з development
        long employeeCountFromDevelopment = underTest.getEmployeeCountForCompanyAndChildren(development, companies);
        assertEquals(7, employeeCountFromDevelopment); // 4 (development) + 3 (main)
    }

    @Test
    void whenChildCompanyWithEmployeesIsQueriedThenCountCorrectly() {
        // Перевіряє, чи метод коректно підраховує кількість працівників дочірньої компанії bookkeeping
        long employeeCountFromBookkeeping = underTest.getEmployeeCountForCompanyAndChildren(bookkeeping, companies);
        assertEquals(5, employeeCountFromBookkeeping); // 2 (bookkeeping) + 3 (main)
    }

    @Test
    void whenCompanyHasNoEmployeesThenReturnZero() {
        // Перевіряє, чи метод повертає 0, якщо у компанії немає працівників
        Company companyWithNoEmployees = new Company(null, 0);
        long employeeCountFromEmptyCompany = underTest.getEmployeeCountForCompanyAndChildren(companyWithNoEmployees, companies);
        assertEquals(0, employeeCountFromEmptyCompany); // Оскільки у компанії немає працівників
    }

    // Тести для методу getEmployeeCountForChildrenAndCompany
    // Виходить тут ми спускаємось по сходинках
    // До прикладу від front-end до її дочірніх - ui та ux

    @Test
    void whenMainCompanyIsQueriedThenReturnTotalEmployeeCount() {
        // Перевіряє, чи метод повертає загальну кількість працівників для головної компанії
        long employeeCount = underTest.getEmployeeCountForChildrenAndCompany(main, companies);
        assertEquals(51, employeeCount); // 3 + 2 + 4 + 11 + 10 + 3 + 5 + 3
    }

    @Test
    void whenFrontEndIsQueriedThenReturnEmployeeCountIncluding() {
        // Перевіряє, чи метод повертає кількість працівників для frontEnd, включаючи його дітей
        long employeeCount = underTest.getEmployeeCountForChildrenAndCompany(frontEnd, companies);
        assertEquals(21, employeeCount); // 11 (frontEnd) + 6 (uiTeam) + 4 (uxTeam)
    }

    @Test
    void whenDevelopmentIsQueriedThenReturnEmployeeCountIncluding() {
        // Перевіряє, чи метод повертає кількість працівників для development, включаючи його дочірніх
        long employeeCount = underTest.getEmployeeCountForChildrenAndCompany(development, companies);
        assertEquals(46, employeeCount); // 4 (development) + 3 (design) + 10 (backEnd) + 5 (businessLogic) + 3 (database) + 11 (frontEnd) + 6 (uiTeam) + 4 (uxTeam)
    }

    @Test
    void whenUiTeamIsQueriedThenReturnEmployeeCountIncludingChildren() {
        // Перевіряє, чи метод повертає кількість працівників для uiTeam, яка не має дітей
        long employeeCount = underTest.getEmployeeCountForChildrenAndCompany(uiTeam, companies);
        assertEquals(6, employeeCount); // 6 (uiTeam)
    }

    @Test
    void whenChildCompaniesCountedThenReturnTotalForMainCompany() {
        // Перевіряє, чи метод повертає кількість працівників для різних дочірніх компаній
        long employeeCount = underTest.getEmployeeCountForChildrenAndCompany(bookkeeping, companies);
        assertEquals(2, employeeCount); // 2 працівники лише в bookkeeping

        employeeCount = underTest.getEmployeeCountForChildrenAndCompany(development, companies);
        assertEquals(46, employeeCount); // 46 працівників у development

        employeeCount = underTest.getEmployeeCountForChildrenAndCompany(frontEnd, companies);
        assertEquals(21, employeeCount); // 21 працівник у frontEnd
    }
}
