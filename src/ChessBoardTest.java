import org.jetbrains.annotations.*;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

class ChessBoardTest {
    ChessBoard chessboard = new ChessBoard();

    String[][] initCase = {{"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
                           {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
                           {" ", " ", " ", " ", " ", " ", " ", " "},
                           {" ", " ", " ", " ", " ", " ", " ", " "},
                           {" ", " ", " ", " ", " ", " ", " ", " "},
                           {" ", " ", " ", " ", " ", " ", " ", " "},
                           {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
                           {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}};

    String __TEST_LOG(@NotNull ArrayList<ChessBoard.PossiblePosition> positions) {
        StringBuilder res = new StringBuilder();
        for (ChessBoard.PossiblePosition pos : positions) {
            res.append(String.format("(%d, %d) ", pos.x, pos.y));
        }
        return res.toString().trim();
    }

    @org.junit.jupiter.api.Test
    void calculatePawnsPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.__generate_custom_board(initCase);

        chessboard.calculatePossibleMovablePosition(1, 0);
        Assertions.assertEquals("(2, 0) (3, 0)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(1, 1);
        Assertions.assertEquals("(2, 1) (3, 1)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(6, 0);
        Assertions.assertEquals("(5, 0) (4, 0)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(6, 1);
        Assertions.assertEquals("(5, 1) (4, 1)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        String[][] case1 = {{ "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜" },
                            { "♟", " ", "♟", "♟", "♟", "♟", "♟", "♟" },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", "♟", " ", " ", " ", " ", " ", " " },
                            { " ", "♙", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { "♙", " ", "♙", "♙", "♙", "♙", "♙", "♙" },
                            { "♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}};

        chessboard.__generate_custom_board(case1);
        chessboard.calculatePossibleMovablePosition(3, 1);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(4, 1);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        String[][] case2 = {{ "♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜" },
                            { " ", "♟", " ", "♟", "♟", "♟", "♟", "♟" },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { "♟", " ", "♟", " ", " ", " ", " ", " " },
                            { " ", "♙", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { "♙", " ", "♙", "♙", "♙", "♙", "♙", "♙" },
                            { "♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}};

        chessboard.__generate_custom_board(case2);
        chessboard.calculatePossibleMovablePosition(4, 1);
        Assertions.assertEquals("(3, 1) (3, 0) (3, 2)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        String[][] case3 = {{ " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", "♟", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", "♙", "♜", "♙", " ", " ", " ", " " },
                            { " ", " ", "♙", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " }};
        chessboard.__generate_custom_board(case3);

        chessboard.calculatePossibleMovablePosition(1, 2);
        Assertions.assertEquals("(2, 2)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        String[][] case4 = {{ " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", " ", " ", " ", " ", " ", " " },
                            { " ", " ", "♟", " ", " ", " ", " ", " " }};
        chessboard.__generate_custom_board(case4);

        chessboard.calculatePossibleMovablePosition(7, 2);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();
    }

    @org.junit.jupiter.api.Test
    void calculateKnightsPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.__generate_custom_board(initCase);

        chessboard.calculatePossibleMovablePosition(0, 1);
        Assertions.assertEquals("(2, 0) (2, 2)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(0, 6);
        Assertions.assertEquals("(2, 5) (2, 7)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(7, 1);
        Assertions.assertEquals("(5, 0) (5, 2)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(7, 6);
        Assertions.assertEquals("(5, 5) (5, 7)", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        String[][] case2 = {{"♜", "♞", "♝", "♛", "♚", "♝", " ", "♜"},
                            {"♟", "♟", "♟", "♟", " ", "♟", " ", "♟"},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", "♞", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", "♘", " ", " ", " ", "♙", " "},
                            {"♙", "♙", "♙", "♙", "♙", "♙", " ", "♙"},
                            {"♖", " ", "♗", "♕", "♔", "♗", "♘", "♖"}};
        chessboard.__generate_custom_board(case2);

        chessboard.calculatePossibleMovablePosition(5, 2);
        Assertions.assertEquals(
                "(3, 1) (3, 3) (7, 1) (4, 0) (4, 4)",
                __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(3, 5);
        Assertions.assertEquals(
                "(1, 4) (1, 6) (5, 4) (5, 6) " +
                         "(2, 3) (2, 7) (4, 3) (4, 7)",
                __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();
    }

    @org.junit.jupiter.api.Test
    void calculateRookPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.__generate_custom_board(initCase);

        chessboard.calculatePossibleMovablePosition(0, 0);
        chessboard.calculatePossibleMovablePosition(0, 7);
        chessboard.calculatePossibleMovablePosition(7, 0);
        chessboard.calculatePossibleMovablePosition(7, 7);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        //
        String[][] case2 = {{"♜", "♞", "♝", "♛", "♚", "♝", "♞", " "},
                            {"♟", "♟", "♟", "♟", "♟", "♟", "♟", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♜", " ", " ", " "},
                            {"♖", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
                            {" ", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}};
        chessboard.__generate_custom_board(case2);

        chessboard.calculatePossibleMovablePosition(4, 0);
        Assertions.assertEquals(
                "(1, 0) (2, 0) (3, 0) (5, 0) " +
                         "(4, 1) (4, 2) (4, 3) (4, 4) " +
                         "(4, 5) (4, 6) (4, 7)",
                __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(3, 4);
        Assertions.assertEquals(
                "(2, 4) (4, 4) (5, 4) (6, 4) " +
                         "(3, 0) (3, 1) (3, 2) (3, 3) " +
                         "(3, 5) (3, 6) (3, 7)",
                __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        //
        String[][] case3 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♙", " ", " ", " "},
                            {" ", " ", " ", "♙", "♜", "♙", " ", " "},
                            {" ", " ", " ", " ", "♙", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case3);
        chessboard.calculatePossibleMovablePosition(3, 4);
        Assertions.assertEquals(
                "(2, 4) (4, 4) (3, 3) (3, 5)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        //
        String[][] case4 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♟", " ", " ", " "},
                            {" ", " ", " ", "♟", "♜", "♟", " ", " "},
                            {" ", " ", " ", " ", "♟", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case4);
        chessboard.calculatePossibleMovablePosition(3, 4);
        Assertions.assertEquals(
                "",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        //
        String[][] case5 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", "♜", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case5);
        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(0, 3) (1, 3) (2, 3) (4, 3) (5, 3) (6, 3) (7, 3) " +
                        "(3, 0) (3, 1) (3, 2) (3, 4) (3, 5) (3, 6) (3, 7)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();
    }

    @org.junit.jupiter.api.Test
    void calculateBishopPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.__generate_custom_board(initCase);

        chessboard.calculatePossibleMovablePosition(0, 2);
        chessboard.calculatePossibleMovablePosition(0, 5);
        chessboard.calculatePossibleMovablePosition(7, 2);
        chessboard.calculatePossibleMovablePosition(7, 5);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        //
        String[][] case1 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", "♝", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case1);

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(4, 4) (5, 5) (6, 6) (7, 7) " +
                         "(4, 2) (5, 1) (6, 0) (2, 4) " +
                         "(1, 5) (0, 6) (2, 2) (1, 1) " +
                         "(0, 0)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        String[][] case2 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", "♗", " ", "♗", " ", " ", " "},
                            {" ", " ", " ", "♝", " ", " ", " ", " "},
                            {" ", " ", "♗", " ", "♗", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case2);

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(4, 4) (4, 2) (2, 4) (2, 2)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        String[][] case3 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", "♟", " ", "♟", " ", " ", " "},
                            {" ", " ", " ", "♝", " ", " ", " ", " "},
                            {" ", " ", "♟", " ", "♟", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case3);

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();
    }

    @org.junit.jupiter.api.Test
    void calculateQueenPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.__generate_custom_board(initCase);

        chessboard.calculatePossibleMovablePosition(7, 3);
        chessboard.calculatePossibleMovablePosition(0, 3);
        Assertions.assertEquals("", __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        String[][] case1 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", "♕", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case1);

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(0, 3) (1, 3) (2, 3) (4, 3) (5, 3) (6, 3) (7, 3) " +
                         "(3, 0) (3, 1) (3, 2) (3, 4) (3, 5) (3, 6) (3, 7) " +
                         "(4, 4) (5, 5) (6, 6) (7, 7) " +
                         "(4, 2) (5, 1) (6, 0) " +
                         "(2, 4) (1, 5) (0, 6) " +
                         "(2, 2) (1, 1) (0, 0)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        String[][] case2 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", "♙", " ", " ", " ", " "},
                            {" ", " ", "♙", "♕", "♙", " ", " ", " "},
                            {" ", " ", " ", "♙", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case2);

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(4, 4) (5, 5) (6, 6) (7, 7) " +
                        "(4, 2) (5, 1) (6, 0) (2, 4) " +
                        "(1, 5) (0, 6) (2, 2) (1, 1) " +
                        "(0, 0)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        String[][] case3 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", "♙", " ", "♙", " ", " ", " "},
                            {" ", " ", " ", "♕", " ", " ", " ", " "},
                            {" ", " ", "♙", " ", "♙", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "}};
        chessboard.__generate_custom_board(case3);

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(0, 3) (1, 3) (2, 3) (4, 3) (5, 3) (6, 3) (7, 3) " +
                         "(3, 0) (3, 1) (3, 2) (3, 4) (3, 5) (3, 6) (3, 7)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();
    }

    @org.junit.jupiter.api.Test
    void calculateKingPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.__generate_custom_board(initCase);
        chessboard.calculatePossibleMovablePosition(0, 4);
        chessboard.calculatePossibleMovablePosition(7, 4);
        Assertions.assertEquals(
                "",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        //
        String[][] case1 = {{" ", " ", " ", " ", "♚", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♔", " ", " ", " "}};
        chessboard.__generate_custom_board(case1);

        chessboard.calculatePossibleMovablePosition(0, 4);
        Assertions.assertEquals(
                "(0, 5) (1, 5) (1, 4) (1, 3) (0, 3)",
                __TEST_LOG(chessboard.__get_possible_pos()));
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(7, 4);
        Assertions.assertEquals(
                "(6, 3) (6, 4) (6, 5) (7, 5) (7, 3)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        //
        String[][] case2 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {"♚", " ", " ", "♚", " ", " ", " ", "♚"},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", "♟", "♟", "♟", " ", " "},
                            {"♚", " ", " ", "♟", "♔", "♟", " ", " "}};
        chessboard.__generate_custom_board(case2);

        chessboard.calculatePossibleMovablePosition(7, 4);
        Assertions.assertEquals(
                "(6, 3) (6, 4) (6, 5) (7, 5) (7, 3)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(3, 0);
        Assertions.assertEquals(
                "(2, 0) (2, 1) (3, 1) (4, 1) (4, 0)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(3, 3);
        Assertions.assertEquals(
                "(2, 2) (2, 3) (2, 4) (3, 4) " +
                         "(4, 4) (4, 3) (4, 2) (3, 2)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(3, 7);
        Assertions.assertEquals(
                "(2, 6) (2, 7) (4, 7) (4, 6) (3, 6)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
        chessboard.__clear_possible_pos();

        chessboard.calculatePossibleMovablePosition(7, 0);
        Assertions.assertEquals(
                "(6, 0) (6, 1) (7, 1)",
                __TEST_LOG(chessboard.__get_possible_pos())
        );
    }

    @org.junit.jupiter.api.Test
    void checkTest() {
        ChessBoard.IS_DEBUG = false;
        String[][] case1 = {{" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♛", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", "♛", " ", " ", " ", " "},
                            {" ", " ", " ", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♔", " ", " ", " "}};
        chessboard.__generate_custom_board(case1);
        chessboard.calculateCheck(2, 4, PlayerColor.white);
        Assertions.assertTrue(chessboard.__get_check_status());

        chessboard.calculateCheck(5, 3, PlayerColor.white);
        Assertions.assertFalse(chessboard.__get_check_status());

        String[][] case2 = {{"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
                            {"♟", "♟", " ", " ", "♟", "♟", "♟", "♟"},
                            {" ", " ", " ", "♟", " ", " ", " ", " "},
                            {" ", "♗", "♟", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♙", " ", " ", " "},
                            {" ", " ", " ", " ", " ", "♘", " ", " "},
                            {"♙", "♙", "♙", "♙", " ", "♙", "♙", "♙"},
                            {"♖", "♘", "♗", "♕", "♔", " ", " ", "♖"}};
        chessboard.__generate_custom_board(case2);
        chessboard.calculateCheck(3, 1, PlayerColor.black);
        Assertions.assertTrue(chessboard.__get_check_status());
    }

    @org.junit.jupiter.api.Test
    void endTest() {
        ChessBoard.IS_DEBUG = false;
        String[][] case1 = {{"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
                            {"♟", "♟", " ", " ", "♟", "♟", "♟", "♟"},
                            {" ", " ", " ", "♟", " ", " ", " ", " "},
                            {" ", "♗", "♟", " ", " ", " ", " ", " "},
                            {" ", " ", " ", " ", "♙", " ", " ", " "},
                            {" ", " ", " ", " ", " ", "♘", " ", " "},
                            {"♙", "♙", "♙", "♙", " ", "♙", "♙", "♙"},
                            {"♖", "♘", "♗", "♕", "♔", " ", " ", "♖"}};
        chessboard.__generate_custom_board(case1);
        chessboard.movePiece(3, 1, 0, 4);
        Assertions.assertTrue(chessboard.__get_end_status());
    }
}
