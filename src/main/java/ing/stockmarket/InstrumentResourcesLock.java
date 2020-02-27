package ing.stockmarket;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

public class InstrumentResourcesLock {

	private AtomicBoolean instrumentFlag = new AtomicBoolean(true);

	private AtomicBoolean shutDownInstrument = new AtomicBoolean(true);

	private static LinkedList<Instruments> instrumentsLinkedList = new LinkedList<Instruments>();

	private volatile int switchingInstrumentFlag = 1;

	public int getSwitchingInstrumentFlag() {
		return switchingInstrumentFlag;
	}

	public void setSwitchingInstrumentFlag(int switchingInstrumentFlag) {
		this.switchingInstrumentFlag = switchingInstrumentFlag;
	}

	public AtomicBoolean getInstrumentFlag() {
		return instrumentFlag;
	}

	public void setInstrumentFlag(AtomicBoolean instrumentFlag) {
		this.instrumentFlag = instrumentFlag;
	}

	public AtomicBoolean getShutDownInstrument() {
		return shutDownInstrument;
	}

	public void setShutDownInstrument(AtomicBoolean shutDownInstrument) {
		this.shutDownInstrument = shutDownInstrument;
	}

	public static LinkedList<Instruments> getInstrumentsLinkedList() {
		return instrumentsLinkedList;
	}

	public static void setInstrumentsLinkedList(LinkedList<Instruments> instrumentsLinkedList) {
		InstrumentResourcesLock.instrumentsLinkedList = instrumentsLinkedList;
	}
}
