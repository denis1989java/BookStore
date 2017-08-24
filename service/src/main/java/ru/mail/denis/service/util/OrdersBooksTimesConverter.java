package ru.mail.denis.service.util;

import ru.mail.denis.models.Order;
import ru.mail.denis.models.OrderBooksTimes;
import ru.mail.denis.models.OrdersBooks;
import ru.mail.denis.service.DTOmodels.BasketDTO;
import ru.mail.denis.service.DTOmodels.BookDTO;
import ru.mail.denis.service.DTOmodels.OrderBookTimesDTO;

/**
 * Created by user on 24.08.2017.
 */
public class OrdersBooksTimesConverter {
    public OrdersBooksTimesConverter() {
    }

    public static OrderBooksTimes converter(OrderBookTimesDTO orderBookTimesDTO){
        OrderBooksTimes orderBooksTimes = new OrderBooksTimes();
        orderBooksTimes.setBookQuantity(orderBookTimesDTO.getBookQuantity());
        orderBooksTimes.setBookid(orderBookTimesDTO.getBookid());
        orderBooksTimes.setBookName(orderBookTimesDTO.getBookName());
        orderBooksTimes.setBookPrice(orderBookTimesDTO.getBookPrice());
        orderBooksTimes.setOrdersBooksTimesId(orderBookTimesDTO.getOrdersBooksTimesId());
        return orderBooksTimes;
    }

    public static OrderBookTimesDTO converter(OrderBooksTimes orderBooksTimes){
        OrderBookTimesDTO orderBookTimesDTO = new OrderBookTimesDTO();
        orderBookTimesDTO.setBookid(orderBooksTimes.getBookid());
        orderBookTimesDTO.setOrdersBooksTimesId(orderBooksTimes.getOrdersBooksTimesId());
        orderBookTimesDTO.setBookQuantity(orderBooksTimes.getBookQuantity());
        orderBookTimesDTO.setBookPrice(orderBooksTimes.getBookPrice());
        orderBookTimesDTO.setBookName(orderBooksTimes.getBookName());
        return orderBookTimesDTO;
    }

    public static OrderBooksTimes setOrdersBooks(Order order, BasketDTO basketDTO){
        OrderBooksTimes orderBooksTimes = new OrderBooksTimes();
        orderBooksTimes.setBookPrice(basketDTO.getBookPrice());
        orderBooksTimes.setBookName(basketDTO.getBookName());
        orderBooksTimes.setBookid(basketDTO.getBookId());
        orderBooksTimes.setBookQuantity(basketDTO.getBookQuantity());
        orderBooksTimes.setOrder(order);
        return orderBooksTimes;
    }
    public static OrderBooksTimes setOrdersBooks(Order order, BookDTO bookDTO){
        OrderBooksTimes orderBooksTimes = new OrderBooksTimes();
        orderBooksTimes.setBookQuantity(bookDTO.getBookQuantity());
        orderBooksTimes.setBookid(bookDTO.getBookId());
        orderBooksTimes.setBookName(bookDTO.getBookName());
        orderBooksTimes.setBookPrice(bookDTO.getBookPrice());
        orderBooksTimes.setOrder(order);
        return orderBooksTimes;
    }
}