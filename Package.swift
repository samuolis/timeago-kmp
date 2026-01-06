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
            url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.4/TimeAgoKMP.xcframework.zip",
            checksum: "fed1cabb55d913c183da0d1aca4c2dbcb502b1c002a39acd5f0bc9b78d939561"
        )
    ]
)
