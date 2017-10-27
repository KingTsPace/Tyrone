package cn.soso.abscract;

public abstract class ServicePackage {

	public double price;

	public ServicePackage(double price) {
		super();
		this.price = price;
	}

	public abstract void showInfo();
}
