package controleur.evenements;

import java.util.EventListener;

public interface ModelListener extends EventListener {
  public void modelChanged(ModelChangedEvent event);
}
