"""
Suspectra Accuracy Visualization
Generates graphs from accuracy data for the report
"""
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from datetime import datetime

# Set style
sns.set_style("whitegrid")
plt.rcParams['figure.figsize'] = (12, 8)

# Read the CSV data
df = pd.read_csv('accuracy_data.csv')

# Create figure with subplots
fig, axes = plt.subplots(2, 2, figsize=(14, 10))
fig.suptitle('Suspectra Face Recognition System - Accuracy Analysis', fontsize=16, fontweight='bold')

# 1. Accuracy vs Threshold
ax1 = axes[0, 0]
ax1.plot(df['Threshold'], df['Accuracy'], marker='o', linewidth=2, markersize=8, color='#2E86AB')
ax1.set_xlabel('Similarity Threshold (%)', fontsize=11)
ax1.set_ylabel('Accuracy (%)', fontsize=11)
ax1.set_title('Overall Accuracy by Threshold', fontsize=12, fontweight='bold')
ax1.grid(True, alpha=0.3)
ax1.set_ylim([0, 105])

# Add annotation for best accuracy
best_idx = df['Accuracy'].idxmax()
ax1.annotate(f"Best: {df.loc[best_idx, 'Accuracy']:.2f}%\n@ {df.loc[best_idx, 'Threshold']:.0f}%",
             xy=(df.loc[best_idx, 'Threshold'], df.loc[best_idx, 'Accuracy']),
             xytext=(10, -30), textcoords='offset points',
             bbox=dict(boxstyle='round,pad=0.5', fc='yellow', alpha=0.7),
             arrowprops=dict(arrowstyle='->', connectionstyle='arc3,rad=0'))

# 2. Precision vs Recall
ax2 = axes[0, 1]
ax2.plot(df['Threshold'], df['Precision'], marker='s', linewidth=2, label='Precision', color='#A23B72', markersize=8)
ax2.plot(df['Threshold'], df['Recall'], marker='^', linewidth=2, label='Recall', color='#F18F01', markersize=8)
ax2.plot(df['Threshold'], df['F1_Score'], marker='D', linewidth=2, label='F1 Score', color='#C73E1D', markersize=8)
ax2.set_xlabel('Similarity Threshold (%)', fontsize=11)
ax2.set_ylabel('Score (%)', fontsize=11)
ax2.set_title('Precision, Recall & F1 Score', fontsize=12, fontweight='bold')
ax2.legend(loc='best', framealpha=0.9)
ax2.grid(True, alpha=0.3)
ax2.set_ylim([0, 105])

# 3. Confusion Matrix Breakdown
ax3 = axes[1, 0]
width = 0.35
thresholds_pos = range(len(df))
ax3.bar([p - width/2 for p in thresholds_pos], df['True_Positives'], width, 
        label='True Positives', color='#06A77D', alpha=0.8)
ax3.bar([p + width/2 for p in thresholds_pos], df['False_Positives'], width,
        label='False Positives', color='#D62246', alpha=0.8)
ax3.set_xlabel('Similarity Threshold (%)', fontsize=11)
ax3.set_ylabel('Count', fontsize=11)
ax3.set_title('True Positives vs False Positives', fontsize=12, fontweight='bold')
ax3.set_xticks(thresholds_pos)
ax3.set_xticklabels([f"{int(t)}%" for t in df['Threshold']], rotation=45)
ax3.legend(loc='best', framealpha=0.9)
ax3.grid(True, alpha=0.3, axis='y')

# 4. ROC-style curve (Recall vs FPR approximation)
ax4 = axes[1, 1]
# Calculate approximate FPR (this is simplified)
total_negatives = df['False_Positives'].max() + 10  # approximation
fpr = df['False_Positives'] / total_negatives * 100

ax4.plot(fpr, df['Recall'], marker='o', linewidth=2.5, markersize=8, color='#6A4C93')
ax4.set_xlabel('False Positive Rate (approx %)', fontsize=11)
ax4.set_ylabel('True Positive Rate / Recall (%)', fontsize=11)
ax4.set_title('ROC-Style Curve', fontsize=12, fontweight='bold')
ax4.grid(True, alpha=0.3)

# Add threshold labels on ROC curve
for i, row in df.iterrows():
    if i % 2 == 0:  # Label every other point
        ax4.annotate(f"{int(row['Threshold'])}%",
                    xy=(fpr.iloc[i], row['Recall']),
                    xytext=(5, 5), textcoords='offset points',
                    fontsize=8, alpha=0.7)

plt.tight_layout()
plt.savefig('accuracy_analysis.png', dpi=300, bbox_inches='tight')
print("✓ Graph saved: accuracy_analysis.png")

# Create second figure - Performance metrics table visualization
fig2, ax = plt.subplots(figsize=(12, 6))
ax.axis('tight')
ax.axis('off')

# Prepare table data
table_data = []
table_data.append(['Threshold', 'Accuracy', 'Precision', 'Recall', 'F1 Score'])
for _, row in df.iterrows():
    table_data.append([
        f"{int(row['Threshold'])}%",
        f"{row['Accuracy']:.2f}%",
        f"{row['Precision']:.2f}%",
        f"{row['Recall']:.2f}%",
        f"{row['F1_Score']:.2f}%"
    ])

table = ax.table(cellText=table_data, cellLoc='center', loc='center',
                colWidths=[0.15, 0.15, 0.15, 0.15, 0.15])
table.auto_set_font_size(False)
table.set_fontsize(10)
table.scale(1, 2)

# Style header row
for i in range(5):
    table[(0, i)].set_facecolor('#2E86AB')
    table[(0, i)].set_text_props(weight='bold', color='white')

# Highlight best F1 score row
best_f1_idx = df['F1_Score'].idxmax() + 1  # +1 for header
for i in range(5):
    table[(best_f1_idx, i)].set_facecolor('#FFEB3B')
    table[(best_f1_idx, i)].set_text_props(weight='bold')

plt.title('Suspectra System - Performance Metrics Summary\n(Highlighted: Best F1 Score)',
         fontsize=14, fontweight='bold', pad=20)
plt.savefig('accuracy_table.png', dpi=300, bbox_inches='tight')
print("✓ Table saved: accuracy_table.png")

# Generate summary statistics
print("\n" + "="*50)
print("SUMMARY STATISTICS")
print("="*50)
best_acc = df.loc[df['Accuracy'].idxmax()]
best_f1 = df.loc[df['F1_Score'].idxmax()]

print(f"\nBest Accuracy: {best_acc['Accuracy']:.2f}% at {best_acc['Threshold']:.0f}% threshold")
print(f"Best F1 Score: {best_f1['F1_Score']:.2f}% at {best_f1['Threshold']:.0f}% threshold")
print(f"\nRecommended Threshold: {best_f1['Threshold']:.0f}%")
print(f"  - Provides best balance between precision and recall")
print(f"  - Accuracy: {best_f1['Accuracy']:.2f}%")
print(f"  - Precision: {best_f1['Precision']:.2f}%")
print(f"  - Recall: {best_f1['Recall']:.2f}%")

print("\n" + "="*50)
print("Files generated:")
print("  ✓ accuracy_analysis.png (graphs)")
print("  ✓ accuracy_table.png (metrics table)")
print("="*50)
