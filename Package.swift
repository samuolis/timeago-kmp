// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "TimeAgoKMP",
    platforms: [
        .iOS(.v14),
        .macOS(.v12)
    ],
    products: [
        .library(
            name: "TimeAgoKMP",
            targets: ["TimeAgoKMP"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "TimeAgoKMP",
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.11/TimeAgoKMP.xcframework.zip",
            checksum: "ac69808b1a5d64cf27924e73b2c9ff99f19926d0bb35a0599b4cffff06278eb9"
        )
    ]
)
