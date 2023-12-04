import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ChessBoardTest {
    ChessBoard chessboard = new ChessBoard();
    void Main() {

    }
    String __TEST_LOG(ArrayList<ChessBoard.PossiblePosition> positions) {
        StringBuilder res = new StringBuilder();
        for (ChessBoard.PossiblePosition pos : positions) {
            res.append(String.format("(%d, %d) ", pos.x, pos.y));
        }
        return res.toString().trim();
    }

    @org.junit.jupiter.api.Test
    void calculatePossibleMovablePosition() {
        // ChessBoard chessboard = new ChessBoard();
        ChessBoard.IS_DEBUG = false;
        chessboard.initiateBoard();

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
    }

    @org.junit.jupiter.api.Test
    void calculateKnightsPosition() {
        ChessBoard.IS_DEBUG = false;
        chessboard.initiateBoard();

        // TODO: Knights 움직임 테스트 작성
    }
}
