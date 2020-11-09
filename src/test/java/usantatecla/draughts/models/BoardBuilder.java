package usantatecla.draughts.models;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {

    private List<String> rows;

    public BoardBuilder() {
        this.rows = new ArrayList<>();
    }

    public BoardBuilder row(String row) {
        assert row.length() == Coordinate.getDimension();

        this.rows.add(row);
        return this;
    }

    public Board build() {
        assert rows.size() == Coordinate.getDimension();

        Board board = new Board();
        for(int i = 0; i < Coordinate.getDimension(); i++) {
            String row = rows.get(i);
            for(int j = 0; j < Coordinate.getDimension(); j++) {
                char cell = row.charAt(j);
                Coordinate coordinate = new Coordinate(i,j);
                if(coordinate.isBlack())
                    board.put(coordinate, pieceOf(cell));
            }
        }
        return board;
    }

    private Piece pieceOf(char cell) {
        if(cell == 'b') return new Pawn(Color.WHITE);
        if(cell == 'B') return new Draught(Color.WHITE);
        if(cell == 'n') return new Pawn(Color.BLACK);
        if(cell == 'N') return new Draught(Color.BLACK);
        return null;
    }

}
