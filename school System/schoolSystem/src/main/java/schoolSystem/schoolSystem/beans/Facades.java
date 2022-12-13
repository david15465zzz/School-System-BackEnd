package schoolSystem.schoolSystem.beans;

import schoolSystem.schoolSystem.facades.ClientFacade;

public class Facades {
    private ClientFacade facade;
    private long lastActive;

    public Facades(ClientFacade facade, long lastActive) {
        this.facade = facade;
        this.lastActive = lastActive;
    }

    public ClientFacade getFacade() {
        return facade;
    }

    public void setFacade(ClientFacade facade) {
        this.facade = facade;
    }

    public long getLastActive() {
        return lastActive;
    }

    public void setLastActive(long lastActive) {
        this.lastActive = lastActive;
    }
}
