package listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import gui.MainFrame;
import items.TradeableBulk;

public class NextButtonBulksListener implements ActionListener {

	private MainFrame frame;
	
	public NextButtonBulksListener(MainFrame frame) {
		this.frame = frame;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(frame.getTradeables().getTradeableItems().size() > 0) {
			
			if(frame.getTradeables().getTradeableItems().size() == 1) {
				frame.getBtn_nextTrade_bulks().setEnabled(false);
			}
			
			String bulkAmount = frame.getTxt_amount_bulks().getText();
			String tradeMessage = frame.getTradeables().getTradeableItems().get(0).generateTradeMessage(Integer.valueOf(bulkAmount));
			StringSelection stringSelection = new StringSelection(tradeMessage);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(stringSelection, null);
			
			TradeableBulk tmpTradeables = new TradeableBulk();
			if(frame.getTradeables().getTradeableItems().size() >= 1) {
				
				for(int i = 1; i < frame.getTradeables().getTradeableItems().size(); i++) {
					tmpTradeables.getTradeableItems().add(frame.getTradeables().getTradeableItems().get(i));
				}
				frame.setTradeables(tmpTradeables);
				frame.getLbl_tradeables_bulks().setText("Tradeables: " + tmpTradeables.getTradeableItems().size());
			}
			
		} else {
			frame.getLbl_tradeables_bulks().setText("Tradeables: " + frame.getTradeables().getTradeableItems().size());
			frame.getBtn_nextTrade_bulks().setEnabled(false);
		}
	}

}