package com.yujunyang.intellij.plugin.sonar.gui.toolwindow;

import java.awt.BorderLayout;
import java.util.List;

import com.intellij.openapi.project.Project;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBPanel;
import com.yujunyang.intellij.plugin.sonar.core.DuplicatedBlocksIssue;
import com.yujunyang.intellij.plugin.sonar.core.Issue;
import com.yujunyang.intellij.plugin.sonar.gui.common.UIUtils;
import com.yujunyang.intellij.plugin.sonar.messages.DuplicatedBlocksIssueClickListener;
import com.yujunyang.intellij.plugin.sonar.messages.IssueClickListener;
import com.yujunyang.intellij.plugin.sonar.messages.MessageBusManager;

public class IssueDetailPanel extends JBPanel implements IssueClickListener, DuplicatedBlocksIssueClickListener {
    private Project project;
    private IssueCodePanel codePanel;
    private IssueDescriptionPanel descriptionPanel;

    public IssueDetailPanel(Project project) {
        this.project = project;
        init();
        MessageBusManager.subscribe(project, this, IssueClickListener.TOPIC, this::click);
        MessageBusManager.subscribe(project, this, DuplicatedBlocksIssueClickListener.TOPIC, this::click);
    }



    public void refresh() {

    }

    @Override
    public void click(List<DuplicatedBlocksIssue> issues) {

    }

    @Override
    public void click(Issue issue) {
        codePanel.refresh(issue);
    }

    private void init() {
        setLayout(new BorderLayout());

        OnePixelSplitter listAndCurrentSplitter = new OnePixelSplitter();
        listAndCurrentSplitter.getDivider().setBackground(UIUtils.borderColor());
        listAndCurrentSplitter.setProportion(0.6f);
        add(listAndCurrentSplitter, BorderLayout.CENTER);

        codePanel = new IssueCodePanel(project);
        listAndCurrentSplitter.setFirstComponent(codePanel);

        descriptionPanel = new IssueDescriptionPanel();
        listAndCurrentSplitter.setSecondComponent(descriptionPanel);
    }
}
