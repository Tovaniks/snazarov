package ru.job4j.chess;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.figures.Figure;
import ru.job4j.chess.figures.Knight;

/**
 * Board.
 *
 * @author Sergey Nazarov
 * @version $Id$
 * @since 2018.03.02
 */
public class Board {

    private static final int ROW = 8;
    private static final int COLUMN = 8;
    private Figure[] figures = new Figure[32];
    private int position = 0;

    /**
     * Валидация клетки
     *
     * @param cell клетка
     * @return возвращает true/false на существование клетки.
     */
    private static boolean validCell(Cell cell) {
        boolean result = true;
        if (cell.getX() < 0 || cell.getX() > ROW - 1 || cell.getY() < 0 || cell.getY() > COLUMN - 1) {
            result = false;
        }
        return result;

    }

    /**
     * Валидация начальной и конечной клетки клетки
     *
     * @param source начало
     * @param dest конец
     * @return возвращает true/false на существование клеток.
     */
    public static boolean validCells(Cell source, Cell dest) {
        boolean result = true;
        if (!(validCell(source) && validCell(dest))) {
            result = false;
        }
        return result;

    }

    /**
     * Добавляем фигуру в массив
     *
     * @param figure фигура
     */
    public void add(Figure figure) {
        figures[position++] = figure;
    }

    /**
     * Двигаем фигуру. Возвращаем результат действия.
     *
     * @param source начало
     * @param dest конец
     * @return true/false передвижения фигуры.
     */
    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        int id = getFigureID(source);
        Figure figure = figures[id];
        Cell[] way = figure.way(source, dest);
        if (!pathIsEmpty(way) && !(figures[id] instanceof Knight)) {
            throw new OccupiedWayException("Путь занят фигурами!");
        }
        figures[id] = figure.copy(dest);
        return true;
    }

    /**
     * Валидированный метод передвижения фигуры
     *
     * @param source начало
     * @param dest конец
     * @return true/false передвижения фигуры.
     */
    public boolean validMove(Cell source, Cell dest) {
        boolean result = false;
        try {
            result = move(source, dest);
        } catch (FigureNotFoundException e) {
            System.out.println("В изначальной клетке нет фигуры!");
        } catch (OccupiedWayException e) {
            System.out.println("Путь занят фигурами!");
        } catch (ImpossibleMoveException e) {
            System.out.println("Фигура не может сюда пойти!");
        }
        return result;
    }

    /**
     * Возвращаем id фигуры в массиве.
     *
     * @param source координата
     * @return id фигуры в массиве.
     */
    private int getFigureID(Cell source) throws FigureNotFoundException {
        int result = -1;
        for (int index = 0; index < figures.length; index++) {
            if (figures[index] != null && figures[index].position.getX() == source.getX() && figures[index].position.getY() == source.getY()) {
                result = index;
                break;
            }
        }
        if (result == -1) {
            throw new FigureNotFoundException("В изначальной клетке нет фигуры!");
        }
        return result;
    }

    /**
     * Проверяем наличие фигур на пути
     *
     * @param cells путь
     * @return true/false наличия фигур.
     */
    private boolean pathIsEmpty(Cell[] cells) {
        boolean result = true;
        if (this.figures == null) {
            result = false;
        } else {
            for (int index = 1; index < cells.length; index++) {
                for (Figure figure : figures) {
                    if (figure != null && figure.position.getX() == cells[index].getX() && figure.position.getY() == cells[index].getY()) {
                        result = false;
                        break;
                    }
                }
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }
}
