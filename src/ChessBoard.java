/**
 * Computer Programming 2023 Fall
 * - Tiny chess game

 * Name: YongIn Kim
 * StudentID#: 2018-17174
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//======================================================Don't modify below===============================================================//
enum PieceType {king, queen, bishop, knight, rook, pawn, none}
enum PlayerColor {black, white, none}

// Name: YongIn Kim
// StudentID#: 2018-17174
public class ChessBoard {
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel chessBoard;
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Piece[][] chessBoardStatus = new Piece[8][8];
    private ImageIcon[] pieceImage_b = new ImageIcon[7];
    private ImageIcon[] pieceImage_w = new ImageIcon[7];
    private JLabel message = new JLabel("Enter Reset to Start");

    ChessBoard(){
        initPieceImages();
        initBoardStatus();
        initializeGui();
    }

    public final void initBoardStatus(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) chessBoardStatus[j][i] = new Piece();
        }
    }

    public final void initPieceImages(){
        pieceImage_b[0] = new ImageIcon(new ImageIcon("./img/king_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[1] = new ImageIcon(new ImageIcon("./img/queen_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[2] = new ImageIcon(new ImageIcon("./img/bishop_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[3] = new ImageIcon(new ImageIcon("./img/knight_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[4] = new ImageIcon(new ImageIcon("./img/rook_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[5] = new ImageIcon(new ImageIcon("./img/pawn_b.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_b[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));

        pieceImage_w[0] = new ImageIcon(new ImageIcon("./img/king_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[1] = new ImageIcon(new ImageIcon("./img/queen_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[2] = new ImageIcon(new ImageIcon("./img/bishop_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[3] = new ImageIcon(new ImageIcon("./img/knight_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[4] = new ImageIcon(new ImageIcon("./img/rook_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[5] = new ImageIcon(new ImageIcon("./img/pawn_w.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH));
        pieceImage_w[6] = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
    }

    public ImageIcon getImageIcon(Piece piece){
        if(piece.color.equals(PlayerColor.black)){
            if(piece.type.equals(PieceType.king)) return pieceImage_b[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_b[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_b[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_b[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_b[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_b[5];
            else return pieceImage_b[6];
        }
        else if(piece.color.equals(PlayerColor.white)){
            if(piece.type.equals(PieceType.king)) return pieceImage_w[0];
            else if(piece.type.equals(PieceType.queen)) return pieceImage_w[1];
            else if(piece.type.equals(PieceType.bishop)) return pieceImage_w[2];
            else if(piece.type.equals(PieceType.knight)) return pieceImage_w[3];
            else if(piece.type.equals(PieceType.rook)) return pieceImage_w[4];
            else if(piece.type.equals(PieceType.pawn)) return pieceImage_w[5];
            else return pieceImage_w[6];
        }
        else return pieceImage_w[6];
    }

    public final void initializeGui(){
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        JButton startButton = new JButton("Reset");
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                initiateBoard();
            }
        });

        tools.add(startButton);
        tools.addSeparator();
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 8));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);
        ImageIcon defaultIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
        Insets buttonMargin = new Insets(0,0,0,0);
        for(int i=0; i<chessBoardSquares.length; i++) {
            for (int j = 0; j < chessBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.addActionListener(new ButtonListener(i, j));
                b.setMargin(buttonMargin);
                b.setIcon(defaultIcon);
                if((j % 2 == 1 && i % 2 == 1)|| (j % 2 == 0 && i % 2 == 0)) b.setBackground(Color.WHITE);
                else b.setBackground(Color.gray);
                b.setOpaque(true);
                b.setBorderPainted(false);
                chessBoardSquares[j][i] = b;
            }
        }

        for (int i=0; i < 8; i++) {
            for (int j=0; j < 8; j++) chessBoard.add(chessBoardSquares[j][i]);

        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessBoard cb = new ChessBoard();
                JFrame f = new JFrame("Chess");
                f.add(cb.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.setResizable(false);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }

    //================================Utilize these functions========================================//

    class Piece{
        PlayerColor color;
        PieceType type;

        Piece(){
            color = PlayerColor.none;
            type = PieceType.none;
        }
        Piece(PlayerColor color, PieceType type){
            this.color = color;
            this.type = type;
        }
    }

    public void setIcon(int x, int y, Piece piece){
        chessBoardSquares[y][x].setIcon(getImageIcon(piece));
        chessBoardStatus[y][x] = piece;
    }

    public Piece getIcon(int x, int y){
        return chessBoardStatus[y][x];
    }

    public void markPosition(int x, int y){
        chessBoardSquares[y][x].setBackground(Color.pink);
    }

    public void unmarkPosition(int x, int y){
        if((y % 2 == 1 && x % 2 == 1)|| (y % 2 == 0 && x % 2 == 0)) chessBoardSquares[y][x].setBackground(Color.WHITE);
        else chessBoardSquares[y][x].setBackground(Color.gray);
    }

    public void setStatus(String inpt){
        message.setText(inpt);
    }

    public void initiateBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) setIcon(i, j, new Piece());
        }
        setIcon(0, 0, new Piece(PlayerColor.black, PieceType.rook));
        setIcon(0, 1, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 2, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 3, new Piece(PlayerColor.black, PieceType.queen));
        setIcon(0, 4, new Piece(PlayerColor.black, PieceType.king));
        setIcon(0, 5, new Piece(PlayerColor.black, PieceType.bishop));
        setIcon(0, 6, new Piece(PlayerColor.black, PieceType.knight));
        setIcon(0, 7, new Piece(PlayerColor.black, PieceType.rook));
        for(int i=0;i<8;i++){
            setIcon(1, i, new Piece(PlayerColor.black, PieceType.pawn));
            setIcon(6, i, new Piece(PlayerColor.white, PieceType.pawn));
        }
        setIcon(7, 0, new Piece(PlayerColor.white, PieceType.rook));
        setIcon(7, 1, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 2, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 3, new Piece(PlayerColor.white, PieceType.queen));
        setIcon(7, 4, new Piece(PlayerColor.white, PieceType.king));
        setIcon(7, 5, new Piece(PlayerColor.white, PieceType.bishop));
        setIcon(7, 6, new Piece(PlayerColor.white, PieceType.knight));
        setIcon(7, 7, new Piece(PlayerColor.white, PieceType.rook));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++) unmarkPosition(i, j);
        }
        onInitiateBoard();
    }
//======================================================Don't modify above==============================================================//	




    //======================================================Implement below=================================================================//
    // ==================== TEST only class methods ====================
    protected ArrayList<PossiblePosition> __get_possible_pos() {
        return possiblePos;
    }
    protected void __clear_possible_pos() { possiblePos.clear(); }
    protected void __generate_custom_board(String[][] testCase) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                setIcon(i, j, piecePair.get(testCase[i][j]));
            }
        }
    }
    protected boolean __get_check_status() {
        return check;
    }

    // Class members used exclusively during testing
    private final Map<String, Piece> piecePair = new HashMap<String, Piece>() {{
        put("♜", new Piece(PlayerColor.black, PieceType.rook));
        put("♞", new Piece(PlayerColor.black, PieceType.knight));
        put("♝", new Piece(PlayerColor.black, PieceType.bishop));
        put("♛", new Piece(PlayerColor.black, PieceType.queen));
        put("♚", new Piece(PlayerColor.black, PieceType.king));
        put("♟", new Piece(PlayerColor.black, PieceType.pawn));
        put("♖", new Piece(PlayerColor.white, PieceType.rook));
        put("♘", new Piece(PlayerColor.white, PieceType.knight));
        put("♗", new Piece(PlayerColor.white, PieceType.bishop));
        put("♕", new Piece(PlayerColor.white, PieceType.queen));
        put("♔", new Piece(PlayerColor.white, PieceType.king));
        put("♙", new Piece(PlayerColor.white, PieceType.pawn));
        put(" ", new Piece(PlayerColor.none, PieceType.none));
    }};

    // Debug Flag
    protected static boolean IS_DEBUG = true;
    enum MagicType { MARK, CHECK, CHECKMATE };
    private int selX, selY;
    private boolean check, checkmate, end;
    private PlayerColor TURN;


    /**
     * The class that stores the possible positions for the selected chess piece to move.
     */
    public static class PossiblePosition {
        int x, y;
        public PossiblePosition(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }
    private final ArrayList<PossiblePosition> possiblePos = new ArrayList<PossiblePosition>();
    private boolean isFirstMove(PlayerColor color, int axis) {
        return ((color == PlayerColor.black && axis == 1) || (color == PlayerColor.white && axis == 6));
    }

    private boolean isEmptySpace(int x, int y) {
        return getIcon(x, y).type == PieceType.none;
    }

    private boolean isEnemyExist(int x, int y, PlayerColor color) {
        if (color == PlayerColor.black) {
            return getIcon(x, y).color == PlayerColor.white;
        }
        return getIcon(x, y).color == PlayerColor.black;
    }

    private boolean isExist(int x, int y, PlayerColor color) {
        return getIcon(x, y).color == color;
    }

    /**
     * Initialize the {@code possiblePos} array.
     */
    private void initializePossiblePos() {
        possiblePos.clear();
    }

    /**
     * A method to calculate the positions where a Pawn can move
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     */
    private void generatePawnPosition(int x, int y, PlayerColor color) {
        if (color == PlayerColor.black) {
            if (x < 7 && isEmptySpace(x + 1, y)) {
                possiblePos.add(new PossiblePosition(x + 1, y));
                if (isFirstMove(color, x) && !isEnemyExist(x + 2, y, color) && isEmptySpace(x + 2, y))
                    possiblePos.add(new PossiblePosition(x + 2, y));
            }
            if (y > 0 && isEnemyExist(x + 1, y - 1, color))
                possiblePos.add(new PossiblePosition(x + 1, y - 1));
            if (y < 7 && isEnemyExist(x + 1, y + 1, color))
                possiblePos.add(new PossiblePosition(x + 1, y + 1));
        } else {
            if (x > 0 && isEmptySpace(x - 1, y)) {
                possiblePos.add(new PossiblePosition(x - 1, y));
                if (isFirstMove(color, x) && !isEnemyExist(x - 2, y, color) && isEmptySpace(x - 2, y))
                    possiblePos.add(new PossiblePosition(x - 2, y));
            }
            if (y > 0 && isEnemyExist(x - 1, y - 1, color))
                possiblePos.add(new PossiblePosition(x - 1, y - 1));
            if (y < 7 && isEnemyExist(x - 1, y + 1, color))
                possiblePos.add(new PossiblePosition(x - 1, y + 1));
        }
    }

    /**
     * A method to calculate the positions where a Knight can move
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     */
    private void generateKnightPosition(int x, int y, PlayerColor color) {
        int[][] positions = {{ -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 },
                       { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }};
        _generatePositions(x, y, color, positions);
    }

    /**
     * A method to calculate the positions where a Rook can move
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     */
    private void generateRookPosition(int x, int y, PlayerColor color) {
        _generateVerticalHorizontalPosition(x, y, color);
    }

    /**
     * A method to calculate the positions where a Bishop can move
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     */
    private void generateBishopPosition(int x, int y, PlayerColor color) {
        _generateDiagonalPosition(x, y, color);
    }

    /**
     * A method to calculate the positions where a Queen can move
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     */
    private void generateQueenPosition(int x, int y, PlayerColor color) {
        _generateVerticalHorizontalPosition(x, y, color);
        _generateDiagonalPosition(x, y, color);
    }

    /**
     * A method to calculate the positions where a King can move
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     */
    private void generateKingPosition(int x, int y, PlayerColor color) {
        int[][] positions = {{ -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 },
                             { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }};
        _generatePositions(x, y, color, positions);
    }

    /**
     * It calculates the movable positions based on predefined coordinate values.
     * @param x x coordinate
     * @param y y coordinate
     * @param color current player color
     * @param predefinedPositions Predefined movement positions of a specific piece
     */
    private void _generatePositions(int x, int y, PlayerColor color, int[][] predefinedPositions) {
        for (int[] pos : predefinedPositions) {
            int posX = x + pos[0];
            int posY = y + pos[1];
            if (isValidPosition(posX, posY) && (isEmptySpace(posX, posY) || isEnemyExist(posX, posY, color)))
                possiblePos.add(new PossiblePosition(posX, posY));
        }
    }
    private void _generateDiagonalPosition(int x, int y, PlayerColor color) {
        for (int i = 1; i < 8; i++) {
            if (isValidPosition(x + i, y + i) && !isExist(x + i, y + i, color)) {
                possiblePos.add(new PossiblePosition(x + i, y + i));
                if (isEnemyExist(x + i, y + i, color)) break;
            }
            else break;
        }
        for (int i = 1; i < 8; i++) {
            if (isValidPosition(x + i, y - i) && !isExist(x + i, y - i, color)) {
                possiblePos.add(new PossiblePosition(x + i, y - i));
                if (isEnemyExist(x + i, y - i, color)) break;
            }
            else break;
        }
        for (int i = 1; i < 8; i++) {
            if (isValidPosition(x - i, y + i) && !isExist(x - i, y + i, color)) {
                possiblePos.add(new PossiblePosition(x - i, y + i));
                if (isEnemyExist(x - i, y + i, color)) break;
            }
            else break;
        }
        for (int i = 1; i < 8; i++) {
            if (isValidPosition(x - i, y - i) && !isExist(x - i, y - i, color)) {
                possiblePos.add(new PossiblePosition(x - i, y - i));
                if (isEnemyExist(x - i, y - i, color)) break;
            }
            else break;
        }
    }
    private void _generateVerticalHorizontalPosition(int x, int y, PlayerColor color) {
        for (int i = 0; i < 8; i++) {
            if (i != x && isPathClear(x, y, i, y) && !isExist(i, y, color))
                possiblePos.add(new PossiblePosition(i, y));
        }
        for (int i = 0; i < 8; i++) {
            if (i != y && isPathClear(x, y, x, i) && !isExist(x, i, color))
                possiblePos.add(new PossiblePosition(x, i));
        }
    }

    private boolean isPathClear(int startX, int startY, int endX, int endY) {
        int deltaX = Integer.compare(endX, startX);
        int deltaY = Integer.compare(endY, startY);
        for (int x = startX + deltaX, y = startY + deltaY; x != endX || y != endY; x += deltaX, y += deltaY) {
            if (!isEmptySpace(x, y)) return false;
        }
        return true;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x <= 7 && y >= 0 && y <= 7;
    }

    /**
     * Calculate the possible positions for the selected chess piece to move.
     * @param from_x x position
     * @param from_y y position
     */
    public void calculatePossibleMovablePosition(int from_x, int from_y) {
        Piece curr = chessBoardStatus[from_y][from_x];
        PieceType type = curr.type;
        PlayerColor color = curr.color;
        __LOG(ANSI_YELLOW + "Piece - %s %s\n" + ANSI_RESET, color, type);
        switch (type) {
            case pawn:
                generatePawnPosition(from_x, from_y, color);
                break;
            case king:
                generateKingPosition(from_x, from_y, color);
                break;
            case rook:
                generateRookPosition(from_x, from_y, color);
                break;
            case queen:
                generateQueenPosition(from_x, from_y, color);
                break;
            case knight:
                generateKnightPosition(from_x, from_y, color);
                break;
            case bishop:
                generateBishopPosition(from_x, from_y, color);
                break;
            default:
                break;
        }
        __print_possible_pos();
    }

    // ==================== DEV-specific class members ====================
    private static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";


    // ==================== DEV-specific class methods ====================
    /**
     * Logging a messages to the console if the {@code IS_DEBUG} flag
     * is defined as {@code true}.
     * @param format
     *        Equivalent to the {@code format} parameter of {@code System.out.printf}.
     * @param args
     *        Equivalent to the {@code args} parameter of {@code System.out.printf}.
     */
    private void __LOG(final String format, Object... args) {
        if (IS_DEBUG) System.out.printf(format, args);
    }
    private String __simplify(PlayerColor color, PieceType type) {
        if (Objects.equals(type.toString(), "none")) return "X";
        String C = color.toString().substring(0, 1).toUpperCase();
        return C + "-" + type;
    }
    private void __print_board() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Piece curr = getIcon(x, y);
                __LOG("%-9s", __simplify(curr.color, curr.type));
            }
            __LOG("\n");
        }
        __LOG("\n");
    }

    public void __print_possible_pos() {
        __LOG(ANSI_YELLOW + "Available move pos:\n" + ANSI_RESET);
        for (PossiblePosition pos : possiblePos) {
            __LOG("(%d, %d) ", pos.x, pos.y);
        }
        __LOG("\n\n");
    }

    class ButtonListener implements ActionListener{
        int x;
        int y;
        ButtonListener(int x, int y){
            this.x = x;
            this.y = y;
        }
        public void actionPerformed(ActionEvent e) {	// Only modify here
            __LOG(ANSI_YELLOW + "Board Clicked - x: %d, y: %d\n" + ANSI_RESET, x, y);

            if (isClickMarked(x, y)) {
                movePiece(selX, selY, x, y);
                removeAllMarks();
                swapTurn();
                calculateCheck(x, y, TURN);
                if (check) {
                    String turn = TURN == PlayerColor.black ? "BLACK" : "WHITE";
                    setStatus(turn + "'s TURN / CHECK");
                }
                return;
            }

            removeAllMarks();

            // (x, y) is where the click event occurred
            if (getIcon(x, y).type != PieceType.none && TURN == getIcon(x, y).color) {
                selX = x;
                selY = y;
                calculatePossibleMovablePosition(x, y);
                for (PossiblePosition pos : possiblePos) {
                    markPosition(pos.x, pos.y);
                }
            }
        }
    }

    void calculateCheck(int x, int y, PlayerColor currentTurn) {
        initializePossiblePos();
        calculatePossibleMovablePosition(x, y);
        for (PossiblePosition pos : possiblePos) {
            Piece curr = getIcon(pos.x, pos.y);
            if (curr.type == PieceType.king && curr.color == currentTurn) {
                check = true;
                initializePossiblePos();
                return;
            }
        }
        check = false;
        initializePossiblePos();
    }

    /**
     *
     */
    void calculateCheckmate() {

    }

    boolean isClickMarked(int x, int y) {
        for (PossiblePosition pos : possiblePos) {
            if (x == pos.x && y == pos.y) return true;
        }
        return false;
    }

    /**
     * Remove all markings on the board and reset {@code possiblePos[]}.
     */
    void removeAllMarks() {
        for (PossiblePosition pos : possiblePos) {
            unmarkPosition(pos.x, pos.y);
        }
        initializePossiblePos();
    }

    /**
     * A callback function that occurs after the Reset button is
     */
    void onInitiateBoard(){
        __LOG(ANSI_GREEN + "===== GAME START =====\n\n" + ANSI_RESET);
        __print_board();
        initializeTurn();
    }

    private void initializeTurn() {
        TURN = PlayerColor.black;
        setStatus("BLACK's TURN");
    }

    /**
     * Swap user's turn and display messages according current state
     */
    private void swapTurn() {
        if (TURN == PlayerColor.black) {
            TURN = PlayerColor.white;
            setStatus("WHITE's TURN");
        } else {
            initializeTurn();
        }
    }

    private void movePiece(int from_x, int from_y, int to_x, int to_y) {
        // Modify chess board status
        Piece temp = getIcon(from_x, from_y);
        chessBoardStatus[to_y][to_x] = chessBoardStatus[from_y][from_x];
        chessBoardStatus[from_y][from_x] = new Piece();
        // Moving icons
        setIcon(to_x, to_y, temp);
        setIcon(from_x, from_y, new Piece());
    }
}
