package study.board_pj.domain;

public enum BoardCategory {
    FREE, GREETING, GOLD;

    public static BoardCategory of(String category) {
        if (category.equalsIgnoreCase("free")) return BoardCategory.FREE;
        else if (category.equalsIgnoreCase("greeting")) return BoardCategory.GREETING;
        else return null;
    }
}

