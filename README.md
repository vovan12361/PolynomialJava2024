# Newton and Lagrange Polynomials

Этот проект реализует полиномы Лагранжа и Ньютона, которые используются для интерполяции данных. В проекте реализованы следующие основные функции:

- Стандартный полином, заданы базовые арифмитические операции.
- Полином Лагранжа, который строится на основе заданных точек и вычисляется с использованием формулы Лагранжа.
- Полином Ньютона, который строится с использованием разделённых разностей и обновляется инкрементально при добавлении новых узлов.

## Описание

Проект представляет собой библиотеку для вычисления полиномов Лагранжа и Ньютона с возможностью инкрементального обновления полинома Ньютона при добавлении новых узлов.

- Реализованы три класса:
    - `Polynomial`: базовый класс полинома.
    - `Lagrange`: для вычисления полинома Лагранжа.
    - `Newton`: для вычисления и инкрементального обновления полинома Ньютона.
## Тест производительности

Тест производился для вычисления полиномов Лагранжа и Ньютона, состоящего из 1000 нод. Во время выполнения теста вычисляются значение в точке x = 500.

:-1:Lagrange time: 26803 ms

:+1:Newton time: 143 ms

:speech_balloon:Newton is 187 times faster than Lagrange