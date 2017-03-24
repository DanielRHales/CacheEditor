package com.frame.impl;

import com.cache.sprite.Bean;
import com.cache.sprite.SpriteLoader;
import com.cache.sprite.handler.SpriteHandler;
import com.cache.sprite.impl.Cached;
import com.cache.sprite.impl.Raw;
import com.cache.sprite.util.BeanType;
import com.cache.util.Type;
import com.frame.component.CachePanel;
import com.frame.listener.ButtonListener;
import com.frame.listener.ListListener;
import com.frame.util.ScrollProperty;
import com.resource.Resource;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @author Daniel
 */
public class SpritePanel extends CachePanel {

    private Bean bean = null;

    @Override
    public Type getType() {
        return Type.SPRITES;
    }

    @Override
    protected SpriteLoader getCacheLoader() {
        return SpriteLoader.getInstance();
    }

    @Override
    protected void initialize() {
        GridBagConstraints gridBagConstraints;

        final JPanel controlPanel = new JPanel();
        final JButton packButton = new JButton();
        final JButton insertAll = new JButton();
        final JButton insertSelected = new JButton();
        final JButton dumpData = new JButton();
        final JButton deleteImage = new JButton();
        final JButton reloadIndex = new JButton();
        final JScrollPane cachedSpriteScroll = new JScrollPane();
        final JList cachedSpriteList = new JList();
        final JScrollPane rawSpriteScroll = new JScrollPane();
        final JList rawSpriteList = new JList();
        final JScrollPane tableScroll = new JScrollPane();
        final JTable spriteTable = new JTable();
        final JLabel spriteLabel = new JLabel();

        final List<Component> components = new ArrayList<Component>(
                Arrays.asList(packButton, insertAll, insertSelected, dumpData, deleteImage, reloadIndex, cachedSpriteList, rawSpriteList, spriteTable)
        );

        controlPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlPanel.setLayout(new GridBagLayout());

        packButton.setIcon(new ImageIcon(Resource.PACKAGE));
        packButton.setToolTipText("Pack Index");
        new ButtonListener(packButton) {
            @Override
            protected void actionPerformed() {
                toggle(components, false);
                EventQueue.invokeLater(new Thread() {
                    @Override
                    public void run() {
                        getCacheLoader().pack();
                        toggle(components, true);
                    }
                });
            }
        };
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        controlPanel.add(packButton, gridBagConstraints);

        insertAll.setIcon(new ImageIcon(Resource.MULTIPLE_PACK));
        insertAll.setToolTipText("Insert All");
        new ButtonListener(insertAll) {
            @Override
            protected void actionPerformed() {
                int index = SpriteHandler.getBeanList(BeanType.CACHED).size();
                for (Bean bean : SpriteHandler.getBeanList(BeanType.RAW)) {
                    final Raw raw = ((Raw) bean);
                    final Cached cached = new Cached();
                    cached.setId(index++);
                    cached.setName(raw.getFile().getName().substring(0, raw.getFile().getName().lastIndexOf('.')));
                    cached.setBytes(raw.getBytes());
                    cached.setImage(raw.getImage());
                    SpriteHandler.submit(cached);
                }
                getCacheLoader().setCachedListModel(cachedSpriteList);
                getCacheLoader().setRawListModel(rawSpriteList);
                final int size = SpriteHandler.getBeanList(BeanType.CACHED).size();
                cachedSpriteList.setSelectedIndex(size);
                cachedSpriteList.ensureIndexIsVisible(size);
            }
        };
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        controlPanel.add(insertAll, gridBagConstraints);

        insertSelected.setIcon(new ImageIcon(Resource.SINGLE_PACK));
        insertSelected.setToolTipText("Insert Selected");
        new ButtonListener(insertSelected) {
            @Override
            protected void actionPerformed() {
                if (getBean() != null) {
                    if (getBean().getBeanType().equals(BeanType.RAW)) {
                        final Raw raw = (Raw) getBean();
                        final Cached cached = new Cached();
                        final int index = SpriteHandler.getBeanList(BeanType.CACHED).size();
                        cached.setId(index);
                        cached.setName(raw.getFile().getName().substring(0, raw.getFile().getName().lastIndexOf('.')));
                        cached.setBytes(raw.getBytes());
                        cached.setImage(raw.getImage());
                        SpriteHandler.submit(cached);
                        setBean(null);
                        getCacheLoader().setCachedListModel(cachedSpriteList);
                        getCacheLoader().setRawListModel(rawSpriteList);
                        cachedSpriteList.setSelectedIndex(index);
                        cachedSpriteList.ensureIndexIsVisible(index);
                    }
                }
            }
        };
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        controlPanel.add(insertSelected, gridBagConstraints);

        dumpData.setIcon(new ImageIcon(Resource.DUMP_DATA));
        dumpData.setToolTipText("Dump Data");
        new ButtonListener(dumpData) {
            @Override
            protected void actionPerformed() {
                toggle(components, false);
                EventQueue.invokeLater(
                        new Thread() {
                            @Override
                            public void run() {
                                getCacheLoader().dumpCachedList();
                                toggle(components, true);
                            }
                        }
                );
            }
        };
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        controlPanel.add(dumpData, gridBagConstraints);

        deleteImage.setIcon(new ImageIcon(Resource.DELETE));
        deleteImage.setToolTipText("Delete Image");
        new ButtonListener(deleteImage) {
            @Override
            protected void actionPerformed() {
                if (getBean() != null) {
                    getCacheLoader().removeBean(getBean());
                    if (getBean().getBeanType().equals(BeanType.CACHED)) {
                        SpriteHandler.removeCachedBean((Cached) getBean());
                        getCacheLoader().setCachedListModel(cachedSpriteList);
                    } else if (getBean().getBeanType().equals(BeanType.RAW)) {
                        SpriteHandler.removeRawBean((Raw) getBean());
                        getCacheLoader().setRawListModel(rawSpriteList);
                    }
                    setBean(null);
                    spriteTable.setModel(new DefaultTableModel());
                    spriteLabel.setIcon(new ImageIcon());
                }
            }
        };
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        controlPanel.add(deleteImage, gridBagConstraints);

        reloadIndex.setIcon(new ImageIcon(Resource.RELOAD));
        reloadIndex.setToolTipText("Reload Index");
        new ButtonListener(reloadIndex) {
            @Override
            @SuppressWarnings("unchecked")
            protected void actionPerformed() {
                setBean(null);
                cachedSpriteList.setModel(new DefaultListModel());
                rawSpriteList.setModel(new DefaultListModel());
                spriteTable.setModel(new DefaultTableModel());
                spriteLabel.setIcon(new ImageIcon());
                toggle(components, false);
                EventQueue.invokeLater(
                        new Thread() {
                            @Override
                            public void run() {
                                getCacheLoader().reload();
                                getCacheLoader().setRawListModel(rawSpriteList);
                                getCacheLoader().setCachedListModel(cachedSpriteList);
                                toggle(components, true);
                            }
                        }
                );
            }
        };
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        controlPanel.add(reloadIndex, gridBagConstraints);

        cachedSpriteScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        cachedSpriteScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        cachedSpriteScroll.setBorder(BorderFactory.createTitledBorder("Cached"));
        cachedSpriteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        new ListListener(cachedSpriteList) {
            @Override
            @SuppressWarnings("all")
            protected void valueChanged() {
                final Cached cached = (Cached) cachedSpriteList.getSelectedValue();
                if (!cachedSpriteList.getValueIsAdjusting()) {
                    if (cached != null) {
                        rawSpriteList.clearSelection();
                        setBean(cached);
                        getCacheLoader().setTableModel(spriteTable, getBean());
                        spriteLabel.setIcon(new ImageIcon(getBean().getImage()));
                    }
                }
            }
        };
        cachedSpriteScroll.setViewportView(cachedSpriteList);
        ScrollProperty.setScrollProperty(cachedSpriteScroll, 10, 0);

        rawSpriteScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        rawSpriteScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        rawSpriteScroll.setBorder(BorderFactory.createTitledBorder("Raw"));
        rawSpriteList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        new ListListener(rawSpriteList) {
            @Override
            @SuppressWarnings("all")
            protected void valueChanged() {
                final Raw rawBean = (Raw) rawSpriteList.getSelectedValue();
                if (!rawSpriteList.getValueIsAdjusting()) {
                    if (rawBean != null) {
                        cachedSpriteList.clearSelection();
                        setBean(rawBean);
                        getCacheLoader().setTableModel(spriteTable, getBean());
                        spriteLabel.setIcon(new ImageIcon(getBean().getImage()));
                    }
                }
            }
        };
        rawSpriteScroll.setViewportView(rawSpriteList);
        ScrollProperty.setScrollProperty(rawSpriteScroll, 10, 0);

        spriteTable.getTableHeader().setResizingAllowed(false);
        spriteTable.getTableHeader().setReorderingAllowed(false);

        tableScroll.setViewportView(spriteTable);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(cachedSpriteScroll, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(rawSpriteScroll, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(tableScroll, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(spriteLabel)).addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(tableScroll, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(spriteLabel).addGap(0, 0, Short.MAX_VALUE)).addComponent(cachedSpriteScroll, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE).addComponent(rawSpriteScroll)).addContainerGap()));
        getCacheLoader().setRawListModel(rawSpriteList);
        getCacheLoader().setCachedListModel(cachedSpriteList);
    }

    private void toggle(List<Component> list, boolean value) {
        for (Component component : list) {
            component.setEnabled(value);
        }
    }

    private Bean getBean() {
        return bean;
    }

    private void setBean(Bean bean) {
        this.bean = bean;
    }

}
