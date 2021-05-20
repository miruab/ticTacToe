package tictactoe

import kotlin.math.abs

fun main() {
    var x0 = 'X'
    val bf = createField()
    printField(bf)
    while (true) {
        print("Enter the coordinates:")
        var xInt = 0
        var yInt = 0
        val str_coord = readLine()!!

        try {
            val (x, y) = str_coord.split(" ")
            xInt = x.toInt()
            yInt = y.toInt()
        } catch (ae: Exception) {
            println("You should enter numbers!")
            continue
        }

        if (xInt !in 1..3 || yInt !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }

        if (bf[xInt-1][yInt-1] != '_') {
            println("This cell is occupied! Choose another one!")
            continue
        } else {
            bf[xInt-1][yInt-1] = x0
            x0 = if (x0 == 'O') 'X' else 'O'
            printField(bf)
            if (checkResult(bf)) {
                break
            }
        }
    }
}

fun createField(): Array<CharArray> {
    val a = arrayOf(
        CharArray(3),
        CharArray(3),
        CharArray(3)
    )

    for (i in 0..2) {
        for (j in 0..2) {
            a[i][j] = '_'
        }
    }
    return a
}

fun printField(f: Array<CharArray>) {
    println("---------")
    for (i in f.indices) {
        print("| ")
        for (j in f[i].indices) {
            print("${f[i][j]} ")
        }
        print("|")
        println()
    }
    print("---------")
    println()
}

fun checkResult(f: Array<CharArray>): Boolean {
    println()
    var cnt_o = 0
    var cnt_x = 0
    var cnt = 0
    for (i in f.indices) {
        for (j in f[i].indices) {
            if (f[i][j] == 'O') {
                cnt_o += 1
            } else if (f[i][j] == 'X') {
                cnt_x += 1
            } else if (f[i][j] == '_' || f[i][j] == ' ') {
                cnt += 1
            }
        }
    }

    val win_x = checkWin(f, 'X')
    val win_o = checkWin(f, 'O')
    var res = false
    if (abs(cnt_o - cnt_x) > 1 || (win_x && win_o)) {
        println("Impossible")
        res = true
    } else if (win_x) {
        println("X wins")
        res = true
    } else if (win_o) {
        println("O wins")
        res = true
    } else if (cnt == 0) {
        println("Draw")
        res = true
    }
    return res
}

fun checkWin(f: Array<CharArray>, c: Char): Boolean {
    return  (f[0][0] == f[0][1] && f[0][0] == f[0][2] && f[0][0] == c) ||
        (f[0][0] == f[1][0] && f[0][0] == f[2][0] && f[0][0] == c) ||
        (f[2][0] == f[2][1] && f[2][0] == f[2][2] && f[2][0] == c) ||
        (f[0][2] == f[1][2] && f[0][2] == f[2][2] && f[0][2] == c) ||
        (f[0][0] == f[1][1] && f[0][0] == f[2][2] && f[0][0] == c) ||
        (f[0][2] == f[1][1] && f[0][2] == f[2][0] && f[0][2] == c) ||
        (f[1][0] == f[1][1] && f[1][0] == f[1][2] && f[1][0] == c) ||
        (f[0][1] == f[1][1] && f[0][1] == f[2][1] && f[0][1] == c)
}

