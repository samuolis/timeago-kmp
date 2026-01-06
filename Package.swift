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
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.9/TimeAgoKMP.xcframework.zip",
            checksum: "542be2c2eab0ca69c8f2b8f980e806d8a68fe1a66847a879950ef13a7c7150e0"
        )
    ]
)
