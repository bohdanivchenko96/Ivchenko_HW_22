import java.util.Objects;

public class Monitor {
    private int monitorPrice;
    private String monitorName;

    public Monitor(int monitorPrice, String monitorName) {
        this.monitorPrice = monitorPrice;
        this.monitorName = monitorName;
    }

    public Monitor() {
    }

    public int getMonitorPrice() {
        return monitorPrice;
    }

    public void setMonitorPrice(int monitorPrice) {
        this.monitorPrice = monitorPrice;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "monitorPrice=" + monitorPrice +
                ", monitorName='" + monitorName + '\'' +
                '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monitor monitor = (Monitor) o;
        return monitorPrice == monitor.monitorPrice &&
                Objects.equals(monitorName, monitor.monitorName);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return Objects.hash(monitorPrice, monitorName);
    }
}
