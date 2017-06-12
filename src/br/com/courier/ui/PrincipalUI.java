package br.com.courier.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrincipalUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUI frame = new PrincipalUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PrincipalUI() {
		setTitle("Courier - Gestor de Encomendas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 536);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setMnemonic('A');
		menuBar.add(mnArquivo);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnCadastros = new JMenu("Cadastros");
		mnCadastros.setMnemonic('c');
		menuBar.add(mnCadastros);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteUI clienteUI = new ClienteUI();
				clienteUI.setVisible(true);
			}
		});
		mnCadastros.add(mntmClientes);
		
		JMenuItem mntmEncomendas = new JMenuItem("Encomendas");
		mntmEncomendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EncomendaUI encomendaUI = new EncomendaUI();
				encomendaUI.setVisible(true);
			}
		});
		mnCadastros.add(mntmEncomendas);
		
		JMenu mnEncomendas = new JMenu("Encomendas");
		menuBar.add(mnEncomendas);
		
		JMenuItem mntmPorCliente = new JMenuItem("Por Cliente");
		mntmPorCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaEncomendaUI consultaUI = new ConsultaEncomendaUI();
				consultaUI.setVisible(true);
				
			}
		});
		mnEncomendas.add(mntmPorCliente);
		
		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);
		
		//JMenuItem mntmBackup = new JMenuItem("Backup");
		//mnAjuda.add(mntmBackup);
		
		//JMenuItem mntmRestaurar = new JMenuItem("Restaurar");
		//mnAjuda.add(mntmRestaurar);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "<html><body><p width='400px' align='center'>Courier é um "
						+ "software gestor de encomendas, de cunho acadêmico, desenvolvido durante a disciplina "
						+ "Desenvolvimento de Sistemas de Informação 2017/1 do CEULS/ULBRA.</p></body></html>");
			}
		});
		mnAjuda.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
