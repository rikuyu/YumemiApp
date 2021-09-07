## YumemiApp
### 概要
URLに問い合わせてContributerを表示するアプリ

### 使用技術など
- MVVM architecture
- Retrofit
- DataBinding
- Dagger-Hilt
- Coroutine
- Navigation
- Fragment
- RecyclerView
- Glide
- Room
- JUnit4, Truth

### 工夫点
- 設計をGoogle developers 公式サイト記載の MVVM architecture とした。
- 画面遷移時に Navigation の SafeArgs を利用した。
- いいね画面の Adapter に パフォーマンスを考慮し、DiffUtil を採用した。
- APIを利用した通信中に通信状態を確認できるように State class を導入した。[付録: ネットワーク ステータスの公開](https://developer.android.com/jetpack/guide?hl=ja)
- 各Contributer の画面からそのContributer のGithubページを表示する際に WebView を採用し、ローディング中にProgressBar を表示するようにした。
- BottomNavigationView のアイコンの色が押下状態によって変わるようにした。
- 機能によってGitブランチをちゃんと使い分けた。
- issue PRを作成し、紐づけ、後から見直し、理解できるようにした。

### うまく実装できなかった点
- Coroutine Flow の導入...正直あまり理解していないので導入できなかった。
- テストの実装...YumemiDao の簡単な（＝ほぼ無意味な）テストを書いたが、意味のあるテストを書くことができなかった。（書き方を知らない）
- Data Bindingの有効活用...DataBinding の強みを活かせてないと思う。（data タグにViewModel を渡して、、、みたいなことができなかった）
- Room の Delete...アイテムを削除してもスムーズにいいね画面が更新されないことがある。（原因不明）
- Event.kt を使用し、一度だけ通知するような遷移ボタンを実装したかったが、実装方法がよくわからなかった。
