package fastfoodnetwork.api.console;

import fastfoodnetwork.api.facade.OrderFacade;
import fastfoodnetwork.common.model.DishStatus;
import fastfoodnetwork.query.dto.DishItemDTO;
import fastfoodnetwork.query.dto.OrderDetailsDTO;
import fastfoodnetwork.query.dto.OrderStatisticsDTO;

import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    private final OrderFacade orderFacade;
    private final Scanner scanner;

    public ConsoleInterface(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice;
        do {
            showMainMenu();
            choice = readIntInput();
            scanner.nextLine(); // Очистка буфера
            handleMainMenuChoice(choice);
        } while (choice != 0);
    }

    private void showMainMenu() {
        System.out.println("\n===== Система заказов клиентов =====");
        System.out.println("1. Создать новый заказ");
        System.out.println("2. Добавить блюдо в заказ");
        System.out.println("3. Удалить блюдо из заказа");
        System.out.println("4. Обновить количество блюда");
        System.out.println("5. Обновить статус блюда");
        System.out.println("6. Завершить заказ");
        System.out.println("7. Показать детали заказа");
        System.out.println("8. Показать статистику заказов");
        System.out.println("9. Показать все заказы");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMainMenuChoice(int choice) {
        try {
            switch (choice) {
                case 0:
                    System.out.println("Выход из программы...");
                    break;
                case 1:
                    createOrder();
                    break;
                case 2:
                    addDishToOrder();
                    break;
                case 3:
                    removeDishFromOrder();
                    break;
                case 4:
                    updateDishQuantity();
                    break;
                case 5:
                    updateDishStatus();
                    break;
                case 6:
                    completeOrder();
                    break;
                case 7:
                    showOrderDetails();
                    break;
                case 8:
                    showOrderStatistics();
                    break;
                case 9:
                    showAllOrders();
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private void createOrder() {
        orderFacade.createOrder();
        System.out.println("Заказ успешно создан!");
    }

    private void addDishToOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("Введите ID блюда: ");
        String dishId = scanner.nextLine().trim();
        System.out.print("Введите количество: ");
        int quantity = readIntInput();
        scanner.nextLine(); // Очистка буфера
        orderFacade.addDishToOrder(orderId, dishId, quantity);
        System.out.println("Блюдо успешно добавлено в заказ!");
    }

    private void removeDishFromOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("Введите ID блюда: ");
        String dishId = scanner.nextLine().trim();
        orderFacade.removeDishFromOrder(orderId, dishId);
        System.out.println("Блюдо успешно удалено из заказа!");
    }

    private void updateDishQuantity() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("Введите ID блюда: ");
        String dishId = scanner.nextLine().trim();
        System.out.print("Введите новое количество: ");
        int newQuantity = readIntInput();
        scanner.nextLine(); // Очистка буфера
        orderFacade.updateDishQuantity(orderId, dishId, newQuantity);
        System.out.println("Количество блюда успешно обновлено!");
    }

    private void updateDishStatus() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        System.out.print("Введите ID блюда: ");
        String dishId = scanner.nextLine().trim();
        System.out.println("Выберите новый статус: 1. ORDERED, 2. IN_PREPARATION, 3. READY");
        int statusChoice = readIntInput();
        scanner.nextLine(); // Очистка буфера
        DishStatus newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = DishStatus.ORDERED;
                break;
            case 2:
                newStatus = DishStatus.IN_PREPARATION;
                break;
            case 3:
                newStatus = DishStatus.READY;
                break;
            default:
                throw new IllegalArgumentException("Неверный выбор статуса");
        }
        orderFacade.updateDishStatus(orderId, dishId, newStatus);
        System.out.println("Статус блюда успешно обновлен!");
    }

    private void completeOrder() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        orderFacade.completeOrder(orderId);
        System.out.println("Заказ успешно завершен!");
    }

    private void showOrderDetails() {
        System.out.print("Введите ID заказа: ");
        String orderId = scanner.nextLine().trim();
        OrderDetailsDTO details = orderFacade.getOrderDetails(orderId);
        System.out.println("\n=== Детали заказа ===");
        System.out.println("ID заказа: " + details.getOrderId());
        System.out.println("Статус: " + details.getStatus());
        System.out.println("Блюда:");
        for (DishItemDTO dish : details.getDishes()) {
            System.out.printf("  - %s: %d (Статус: %s)%n", dish.getDishId(), dish.getQuantity(), dish.getStatus());
        }
    }

    private void showOrderStatistics() {
        OrderStatisticsDTO stats = orderFacade.getOrderStatistics();
        System.out.println("\n=== Статистика заказов ===");
        System.out.println("Всего заказов: " + stats.getTotalOrders());
        System.out.println("Завершенных заказов: " + stats.getCompletedOrders());
        System.out.println("Среднее количество блюд в заказе: " + String.format("%.2f", stats.getAverageDishesPerOrder()));
    }

    private void showAllOrders() {
        List<OrderDetailsDTO> orders = orderFacade.getAllOrderDetails();
        if (orders.isEmpty()) {
            System.out.println("Нет доступных заказов.");
            return;
        }
        System.out.println("\n=== Все заказы ===");
        for (OrderDetailsDTO order : orders) {
            System.out.println("ID заказа: " + order.getOrderId());
            System.out.println("Статус: " + order.getStatus());
            System.out.println("Блюда:");
            for (DishItemDTO dish : order.getDishes()) {
                System.out.printf("  - %s: %d (Статус: %s)%n", dish.getDishId(), dish.getQuantity(), dish.getStatus());
            }
            System.out.println("-----------------------------");
        }
    }

    private int readIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Очистка некорректного ввода
            return -1;
        }
    }
}