package ru.vsu.cs.course1.graph.task_7;

import ru.vsu.cs.course1.graph.Graph;

import java.util.*;

public class CycleFinder {
    private static Graph graph;
    private static TreeSet<Integer> checkedVertices;
    private static Vector<Integer> currCycle;
    private static Vector<Vector<Integer>> vectorWithCycles = new Vector<>();

    public static Vector<Vector<Integer>> findCycles(Graph graph, TreeSet<Integer> forbiddenVertices){
        CycleFinder.graph = graph;
        CycleFinder.currCycle = new Vector<>();

        for (int currVertex = 0; currVertex < graph.vertexCount(); currVertex++){
            if (!forbiddenVertices.contains(currVertex)) {

                CycleFinder.checkedVertices = forbiddenVertices;
                currCycle.clear();
                currCycle.add(currVertex);

                checkForEndOfCycle(currVertex);

            }
        }
        return vectorWithCycles;
    }

    //проверка цикла на конец (если попали в вершину,
    // которачя была началом цикла, записываем в вектор)
    private static void checkForEndOfCycle(int v){
        for (int adjVertex : graph.adjacencies(v)){

            if ((!checkedVertices.contains(adjVertex)) && ((currCycle.size() < 2) || (adjVertex != currCycle.get(currCycle.size() - 2)))) {
                //if (adjVertex != currCycle.get(0))

                if (adjVertex == currCycle.get(0)) {
                    //найден конец цикла, записываем в вектор
                    //if (currCycle.size() != 2){
                        Vector<Integer> addCycle = (Vector<Integer>) currCycle.clone();
                        addCycle.add(addCycle.get(0));
                        vectorWithCycles.add(addCycle); //надо будет проверить, ссылку он добавляет или копию
                   // }
                    //checkedVertices.add(adjVertex);
                } else {
                    currCycle.add(adjVertex);
                    checkedVertices.add(adjVertex);
                    checkForEndOfCycle(adjVertex);
                    checkedVertices.remove(adjVertex);
                    currCycle.removeElementAt(currCycle.size() - 1);
                }
            }
        }

    }
}
