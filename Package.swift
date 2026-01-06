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
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.8/TimeAgoKMP.xcframework.zip",
            checksum: "eaf1c2bbadf201634c9ea20d4f7ab219dcd949c8902d1f6cbe3b73e393e30c02"
        )
    ]
)
