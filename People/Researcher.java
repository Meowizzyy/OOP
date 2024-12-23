package People;

import ResearcherStuff.ResearchPaper;

public class Researcher extends User {
    public Researcher(String username, String password, String name, String surname) {
        super(username, password, null, null, null, name, surname);
        setIsResearcher(true); // Устанавливаем флаг, что это исследователь
    }

    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
        setHIndex(calculateHIndex());
    }

    public boolean canBeSupervisor() {
        return eligibleToBeSupervisor();
    }

    public String viewResearchPapers(String comparatorType) {
        return printPapers(comparatorType);
    }

    public void listResearchPapers() {
        if (researchPapers.isEmpty()) {
            System.out.println("Нет опубликованных работ.");
        } else {
            System.out.println("Список публикаций:");
            for (ResearchPaper paper : researchPapers) {
                System.out.println("- Название: " + paper.getTitle());
                System.out.println("  Дата публикации: " + paper.getDatePublished());
                System.out.println("  Автор: " + paper.getAuthor().getName() + " " + paper.getAuthor().getSurname());
                System.out.println("  Страниц: " + paper.getPages());
                System.out.println("  Издатель: " + paper.getPublisher());
                System.out.println("  Цитирование: " + paper.getCitiation());
                System.out.println();
            }
        }
    }

}
