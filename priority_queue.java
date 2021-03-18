package priorityQueueLab;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class priority_queue<T extends Comparable<T>> {
    private T[] array; // контейнер для хранения данных в виде двоичной кучи
    private int length;// количество элементов в очереди

    // конструктор с заданной длиной
    public priority_queue(int capacity) {
        array = (T[]) new Comparable[capacity];
        length = 0;

    }
 
    // проверка очереди на простоту
    public boolean isEmpty() {
        return length == 0;
    }

    // получение числа элементов в очереди
    public int size() { 
        return length;
    }

    //добавление элемента в очередь
    public void add(T value){
        //если массив заполнен полностью, увеличиваем объем массива на 1
        if(this.length >= array.length-1){
            array = this.newSpace();
        }
        // увеличиваем количество элементов на 1
        length++;
        // вставляем значение в конец очереди
        array[length] = value;
        //ищем его место в очереди
        goUp();
        System.out.println("Added " + value);

    }
    private T[] newSpace(){
        return Arrays.copyOf(array, array.length * 2);
    }
    
    
    // меняем местами два соседних элемента
    private void swap(int firstIndex, int secondIndex){
        T temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }
    
    //удаление элемента из очереди (максимального/первого элемента приоритетной очереди)
    public T remove(){
        // если очередь пустая, возвращаем null
        if(isEmpty()) return null;
        // получаем наибольший элемент
        T elementToDelete = peek();
        swap(1, length);
        array[length] = null;
        length--;
        goDown();
        System.out.println("deleted element = " + elementToDelete);
        return elementToDelete;
    }
    
    // вовзращает значение максимального элемента
    public T peek(){
        if(isEmpty()) throw new IllegalStateException();
        return array[1];
    }

    private void goUp(){
        int index = length;
        // цикл проверяет на наличие родителя и, если значение родителя меньше вставляемого значения
        // то меняем их местами 
        while (isParent(index) && (parentValue(index).compareTo(array[index]) < 0)) {
				swap(index, parentIndex(index));
				index = parentIndex(index);
			}
    }
    
    private void goDown(){
        int index = 1;
        while (hasLeftChild(index)){
				int larger = leftIndex(index);
				if (hasRightChild(index) && array[leftIndex(index)].compareTo(array[rightIndex(index)]) < 0){
					larger = rightIndex(index);
				}
				if (array[index].compareTo(array[larger]) < 0){
					swap(index, larger);
				}
				else break;

				index = larger;
			}				
    }
    // проверка на наличие родителя
    private boolean isParent(int i){
		return i > 1;
	}
	// возвращает левый индекс
	private int leftIndex(int i){
		return i * 2;
	}
	//возвращает правый индекс
	private int rightIndex(int i){
		return i * 2 + 1;
	}
	// возвращает true если есть левый ребенок
	private boolean hasLeftChild(int i){
		return leftIndex(i) <= length;
	}
	// возвращает true если есть правый ребенок
	private boolean hasRightChild(int i){
		return rightIndex(i) <= length;
	}
	// вовзращает индекс родителя
	private int parentIndex(int i){
		return i / 2;
	}
	// вовзращает значение родителя
	private T parentValue(int i){
 		return array[parentIndex(i)];
	}
    // переопределение метода toString() для вывода значений в очереди
    public String toString(){
		String retval = "";
		for (T each : array){
			if (each != null) retval += each + "  ";
		}
		return retval + "\n";
	}
}