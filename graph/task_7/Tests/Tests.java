package ru.vsu.cs.course1.graph.task_7.Tests;

import com.sun.source.tree.Tree;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.vsu.cs.course1.graph.AdjMatrixGraph;
import ru.vsu.cs.course1.graph.Graph;
import ru.vsu.cs.course1.graph.GraphUtils;
import ru.vsu.cs.course1.graph.task_7.CycleFinder;

import java.util.*;

@RunWith(Parameterized.class)
public final class Tests {

    @Parameterized.Parameter
    public @NotNull TestCase testCase;

    @Parameterized.Parameters
    public static @NotNull
    Collection<TestCase> data(){
        List<TestCase> resList = Arrays.asList(
                new TestCase (new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}},
                        new int[] {},
                new int[][] {{0, 1, 2, 0}, {0, 1, 2, 3, 0}, {0, 2, 3, 0}}),

                new TestCase (new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}},
                        new int[] {1},
                        new int[][] {{0, 2, 3, 0}, {0, 2, 3, 0}}),

                new TestCase (new int[][] {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {2, 3}},
                        new int[] {1, 2},
                        new int[][] {})


        );
        return resList;
    }

    public static final class TestCase {
        public final int[][] matrixAdj;
        public final int[] vorbVert;
        public final int[][] Cycles;

        public TestCase(int[][] matrixAdj, int[] vorbVert, int[][] Cycles) {
            this.matrixAdj = matrixAdj;
            this.vorbVert = vorbVert;
            this.Cycles = Cycles;
        }
    }

    @Test
    public void test() {
        Graph graph = new AdjMatrixGraph();
        TreeSet<Integer> vorbVertTree = new TreeSet<>();
        toTree(testCase.vorbVert,vorbVertTree);
        Vector<Vector<Integer>> Cycles = CycleFinder.findCycles(graph, vorbVertTree);
        int[][] intCycles = GraphUtils.toMatrix(Cycles);

        Assert.assertArrayEquals(intCycles, testCase.Cycles);
    }

    private void toTree(int[] vorbVertInt, TreeSet<Integer> vorbVertTree) {
        for (int i = 0; i < vorbVertInt.length; i++) {
            vorbVertTree.add(vorbVertInt[i]);
        }
    }
}