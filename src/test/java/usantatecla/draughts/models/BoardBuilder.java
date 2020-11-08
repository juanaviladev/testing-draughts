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
            for(int j = 0; j < Coordinate.getDimension(); j++) {
                Coordinate coordinate = new Coordinate(i,j);
                if(coordinate.isBlack() && (Color.BLACK.isInitialRow(i) || Color.WHITE.isInitialRow(i)))
                    board.put(coordinate, new Pawn(Color.getInitialColor(coordinate)));
            }
        }
        return board;
    }

}
