# ConditionPoint Plugin 説明書

## 概要
条件付きポイント集めゲーム<br>
様々なゲームルールがあるなかでポイントを集めていくゲーム<br>
制限時間内で最終的に多くのポイントを稼いだチームの勝利

## Plugin Information
Author : Sitoa <br>
twitter:@torumitsutake<br>
MinecraftVersion : 1.12.2<br>
License: This software is released under the MIT License, see LICENSE.txt.



## Command
### /setteam [playername] [red/blue]
チームにプレイやーを追加するコマンド


### /gamestart
ゲームスタートするためのコマンド

### /gamereset
ゲーム終了後、またはゲーム中にリセットするコマンド<br>
一回ゲームを行った後はこのコマンドでリセットしてください。

### /setgametime [minutes]
ゲーム時間を設定するコマンド<br>
ゲーム時間は分単位でしてください。

### /startpoint [red/blue/spawn]
ゲーム開始時に各チームがどこからスタートするかを指定するコマンド<br>
spawn はゲーム終了時に全プレイヤーがそこにテレポートされる<br>

このコマンドは実行したプレイヤーの位置を参照するため、コマンドの実行はプレイヤー側から行ってください。<br>


## config.yml 設定内容
### gameconfig.gametime:  [Integer]
ゲームの時間を設定している<br>
単位は分

### gameconfig.startlocation.[team].[x,y,z]
ゲームの開始時、終了時のテレポート先を設定している。<br>
チーム名には[red/blue/spawn]がある。

### ruleconfig.[GameRule]: [boolean]
ゲーム用の隠しルールの有効化を設定している。





## プラグインの基本機能
### 1.チェストロック機能
このゲームにはチェストがロックされる機能が追加されています。<br>
チェストロックには鉄ブロック一個が必要で金ブロックを手に持った状態でチェストを叩くと対象チェスト（ラージチェストの場合はそれを含めて）がチームによってロックされます。<br>
ロックされたチェストは破壊、敵チームのアクセスが禁止されます。

しかし、金ブロックでロックされたチェストを叩くことでロックを解錠することができます。<br>
解除されたチェストはアクセス、破壊は可能です。<br>


### 2.鉱石のポイント
基本的な鉱石のポイントは以下の通りです。
ダイヤモンド：50pt<br>
エメラルド: 40pt<br>
ラピスラズリブロック: 30pt<br>
金インゴット: 20pt<br>
レッドストーンブロック: 15pt<Br>
鉄インゴット: 10pt<br>

このほかにもポイントを設定しているアイテムはあります。<br>
また、各鉱石はブロックにすると+1鉱石分のボーナスポイントがあります<br>
例: ダイヤモンドブロック:　500pt(鉱石１０個分)<br>
ブロックとしてポイントが付与されているラピスラズリとレッドストーンについてはボーナスはありません。<br>


## プログラマーの方へのルール追加方法
Rulesディレクトリ内にルールクラスを作って、
BaseRule (リスナーを使用する場合はBaseRuleListener)を継承してください。
<br>
例はRulesの中にありますので必要に応じてご覧ください。
<br>
その後GameRuleManagerクラスのEnum変数内に以下のように追加してください<br>
### [ルール名（任意）]("[コンフィグに書く名前]", new [追加ルールクラス]())


最後にconfig.ymlのルール項目に<br>
### [コンフィグに書く名前]: true <br>
とかくと適用されます


コンフィグに書く名前は英語で書いてください。

良いルールで、マージリクエストもらえれば本体に追加したいと思います。<br>