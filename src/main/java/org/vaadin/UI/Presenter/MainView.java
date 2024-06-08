package org.vaadin.UI.Presenter;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.web.client.RestTemplate;


@Route("")
public class MainView extends VerticalLayout {

    private Div responseDiv;
    private final RestTemplate restTemplate;


    public MainView() {
        this.restTemplate = new RestTemplate();

        responseDiv = new Div();
        Button button = new Button("Test Backend", e -> testBackend());
        add(button, responseDiv);
    }

    private void testBackend() {
        String response = restTemplate.getForObject("http://localhost:8080/user/test", String.class);
        responseDiv.setText(response);
        //show the response in a notification popup
        Notification.show(response);


    }
}
