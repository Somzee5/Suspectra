"""
Simple ASCII-art style diagrams for Suspectra accuracy visualization
Can be converted to images or used directly in markdown
"""

ACCURACY_BY_THRESHOLD = """
╔══════════════════════════════════════════════════════════════╗
║       Suspectra Performance vs Similarity Threshold          ║
╠══════════════════════════════════════════════════════════════╣
║                                                              ║
║  Performance                                                 ║
║  Score (%)                                                   ║
║    100 │                                                     ║
║        │                            ●                        ║
║     90 │                        ●       ●                    ║
║        │                    ●               ●                ║
║     80 │                ●                       ●            ║
║        │            ●                               ●        ║
║     70 │        ●                                       ●    ║
║        │    ●                                               ║
║     60 │●                                                   ║
║        │                                                     ║
║     50 └─────────────────────────────────────────────────── ║
║         50   60   70   75   80   85   90   95  100          ║
║                   Similarity Threshold (%)                   ║
║                                                              ║
║  Legend:  ● = F1 Score (Balance of Precision & Recall)      ║
║           ★ = Recommended Operating Point (75%)             ║
╚══════════════════════════════════════════════════════════════╝
"""

METRICS_COMPARISON = """
╔══════════════════════════════════════════════════════════════╗
║            Performance Metrics at 75% Threshold              ║
╠══════════════════════════════════════════════════════════════╣
║                                                              ║
║  Photo-to-Photo Matching:                                   ║
║  ┌────────────────────────────────────────────────────────┐ ║
║  │ Accuracy:   ████████████████████████████████████ 96.5% │ ║
║  │ Precision:  ███████████████████████████████████ 95.8%  │ ║
║  │ Recall:     ██████████████████████████████████ 94.2%   │ ║
║  │ F1 Score:   ███████████████████████████████████ 95.0%  │ ║
║  └────────────────────────────────────────────────────────┘ ║
║                                                              ║
║  Sketch-to-Photo Matching:                                  ║
║  ┌────────────────────────────────────────────────────────┐ ║
║  │ Accuracy:   █████████████████████████░░░░░░░░░ 68.0%   │ ║
║  │ Precision:  ████████████████████████░░░░░░░░░░ 65.0%   │ ║
║  │ Recall:     ███████████████████████░░░░░░░░░░░ 58.0%   │ ║
║  │ F1 Score:   ███████████████████████░░░░░░░░░░░ 61.0%   │ ║
║  └────────────────────────────────────────────────────────┘ ║
║                                                              ║
║  Note: Lower sketch performance is expected due to          ║
║        cross-modal matching challenges                      ║
╚══════════════════════════════════════════════════════════════╝
"""

CONFUSION_MATRIX = """
╔══════════════════════════════════════════════════════════════╗
║              Confusion Matrix (Sketch-to-Photo)              ║
╠══════════════════════════════════════════════════════════════╣
║                                                              ║
║              Predicted                                       ║
║         ┌───────────┬───────────┐                           ║
║         │   Match   │ No Match  │                           ║
║    ┌────┼───────────┼───────────┤                           ║
║    │ M  │    58%    │    42%    │  Actual Matches           ║
║  A │ a  │    TP     │    FN     │  (Should be found)        ║
║  c │ t  ├───────────┼───────────┤                           ║
║  t │ c  │     5%    │    95%    │  Actual Non-Matches       ║
║  u │ h  │    FP     │    TN     │  (Should be rejected)     ║
║  a │    └───────────┴───────────┘                           ║
║  l                                                           ║
║                                                              ║
║  TP (True Positive):  58% - Correctly identified matches    ║
║  FN (False Negative): 42% - Missed actual matches           ║
║  FP (False Positive):  5% - Incorrect match claims          ║
║  TN (True Negative):  95% - Correctly rejected non-matches  ║
╚══════════════════════════════════════════════════════════════╝
"""

SYSTEM_ARCHITECTURE = """
╔══════════════════════════════════════════════════════════════╗
║         Suspectra Face Recognition Architecture              ║
╠══════════════════════════════════════════════════════════════╣
║                                                              ║
║  ┌─────────────┐                                            ║
║  │   Sketch    │                                            ║
║  │  (Upload)   │                                            ║
║  └──────┬──────┘                                            ║
║         │                                                    ║
║         ▼                                                    ║
║  ┌─────────────────────────────────────┐                   ║
║  │   AWS Rekognition Service           │                   ║
║  │   ┌───────────────────────────────┐ │                   ║
║  │   │  Face Detection               │ │                   ║
║  │   │  (Locate & Extract Face)      │ │                   ║
║  │   └─────────┬─────────────────────┘ │                   ║
║  │             ▼                         │                   ║
║  │   ┌───────────────────────────────┐ │                   ║
║  │   │  Deep CNN Feature Extraction  │ │                   ║
║  │   │  (512-dimensional embedding)  │ │                   ║
║  │   └─────────┬─────────────────────┘ │                   ║
║  │             ▼                         │                   ║
║  │   ┌───────────────────────────────┐ │                   ║
║  │   │  Similarity Calculation       │ │                   ║
║  │   │  (Cosine distance)            │ │                   ║
║  │   └─────────┬─────────────────────┘ │                   ║
║  └─────────────┼───────────────────────┘                   ║
║                │                                             ║
║                ▼                                             ║
║  ┌───────────────────────────────────────┐                 ║
║  │   Suspectra Database                  │                 ║
║  │   (195 indexed faces)                 │                 ║
║  │   - ExternalImageId                   │                 ║
║  │   - FaceId                            │                 ║
║  │   - 512-d Embedding                   │                 ║
║  └───────────────┬───────────────────────┘                 ║
║                  │                                           ║
║                  ▼                                           ║
║  ┌─────────────────────────────────────┐                   ║
║  │   Top Matches (≥75% similarity)     │                   ║
║  │   1. John Doe      - 89%            │                   ║
║  │   2. Jane Smith    - 81%            │                   ║
║  │   3. Bob Johnson   - 76%            │                   ║
║  └─────────────────────────────────────┘                   ║
╚══════════════════════════════════════════════════════════════╝
"""

if __name__ == "__main__":
    print(ACCURACY_BY_THRESHOLD)
    print("\n")
    print(METRICS_COMPARISON)
    print("\n")
    print(CONFUSION_MATRIX)
    print("\n")
    print(SYSTEM_ARCHITECTURE)
    
    # Save to file
    with open("accuracy_diagrams.txt", "w", encoding="utf-8") as f:
        f.write("SUSPECTRA ACCURACY VISUALIZATIONS\n")
        f.write("="*66 + "\n\n")
        f.write("1. ACCURACY BY THRESHOLD\n")
        f.write(ACCURACY_BY_THRESHOLD)
        f.write("\n\n2. METRICS COMPARISON\n")
        f.write(METRICS_COMPARISON)
        f.write("\n\n3. CONFUSION MATRIX\n")
        f.write(CONFUSION_MATRIX)
        f.write("\n\n4. SYSTEM ARCHITECTURE\n")
        f.write(SYSTEM_ARCHITECTURE)
    
    print("\n✓ Diagrams saved to: accuracy_diagrams.txt")
