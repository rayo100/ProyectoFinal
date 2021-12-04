package Dominio;

import Presentacion.BoardPanel;

import java.awt.*;
import java.util.ArrayList;

public class TetrominoeC {
    private final boolean[][] TYPEO = {
        {
                true, true,
                true, true,
        },
        {
                true, true,
                true, true,
        },
        {
                true, true,
                true, true,
        },
        {
                true, true,
                true, true,
        }
    };
    private final boolean[][] TYPES = {
            {
                    false, true, true,
                    true, true, false,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, true,
                    false, false, true,
            },
            {
                    false, false, false,
                    false, true, true,
                    true, true, false,
            },
            {
                    true, false, false,
                    true, true, false,
                    false, true, false,
            }
    };
    private final boolean[][] TYPET = {
            {
                    false, true, false,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, true,
                    false, true, false,
            },
            {
                    false, false, false,
                    true, true, true,
                    false, true, false,
            },
            {
                    false, true, false,
                    true, true, false,
                    false, true, false,
            }
    };
    private final boolean[][] TYPEL = {
            {
                    false, false, true,
                    true, true, true,
                    false, false, false,
            },
            {
                    false, true, false,
                    false, true, false,
                    false, true, true,
            },
            {
                    false, false, false,
                    true, true, true,
                    true, false, false,
            },
            {
                    true, true, false,
                    false, true, false,
                    false, true, false,
            }
    };
    private final boolean[][] TYPEI = {
            {
                    false, false, false, false,
                    true, true, true, true,
                    false, false, false, false,
                    false, false, false, false,
            },
            {
                    false, false, true, false,
                    false, false, true, false,
                    false, false, true, false,
                    false, false, true, false,
            },
            {
                    false, false, false, false,
                    false, false, false, false,
                    true, true, true, true,
                    false, false, false, false,
            },
            {
                    false, true, false, false,
                    false, true, false, false,
                    false, true, false, false,
                    false, true, false, false,
            }
    };
    private final boolean[][][] types = {TYPEO,TYPEI,TYPET,TYPES,TYPEL};
    private final Color[] colors = {
            new Color(BoardPanel.COLOR_MAX, BoardPanel.COLOR_MAX,BoardPanel.COLOR_MIN),
            new Color(BoardPanel.COLOR_MIN,BoardPanel.COLOR_MAX,BoardPanel.COLOR_MAX),
            new Color(128,BoardPanel.COLOR_MIN,128),
            new Color(BoardPanel.COLOR_MIN,BoardPanel.COLOR_MAX,BoardPanel.COLOR_MIN),
            new Color(BoardPanel.COLOR_MAX,127,BoardPanel.COLOR_MIN)
    };
    private final int[] DIMENSIONS = {2,4,3,3,3};

    private final int[] COLUMNS = {2,4,3,3,3};

    private final int[] ROWS = {2,1,2,2,2};


}
