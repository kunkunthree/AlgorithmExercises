package xie.util;

public class FiveTuple<A,B,C,D,E> extends FourTuple<A, B, C, D> {
	public final E fifth;
	public FiveTuple(A a, B b, C c,D d,  E e) {
		super(a, b, c, d);
		this.fifth = e;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + first + ", " + second + ", third = " +third + ", fourth = " +fourth + ", fifth = " +fifth + ")";
	}
}
