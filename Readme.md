#ConditionPoint Plugin 説明書

##概要
条件付きポイント集めゲーム<br>
様々なゲームルールがあるなかでポイントを集めていくゲーム<br>
制限時間内で最終的に多くのポイントを稼いだチームの勝利

##Plugin Information
Author : Sitoa <br>
twitter:@torumitsutake<br>
MinecraftVersion : 1.12.2<br>
License: This software is released under the MIT License, see LICENSE.txt.



##Command
###/setteam [playername] [red/blue]
チームにプレイやーを追加するコマンド


###/gamestart
ゲームスタートするためのコマンド

###/gamereset
ゲーム終了後、またはゲーム中にリセットするコマンド<br>
一回ゲームを行った後はこのコマンドでリセットしてください。

###/setgametime [minutes]
ゲーム時間を設定するコマンド<br>
ゲーム時間は分単位でしてください。

###/startpoint [red/blue/spawn]
ゲーム開始時に各チームがどこからスタートするかを指定するコマンド<br>
spawn はゲーム終了時に全プレイヤーがそこにテレポートされる<br>

このコマンドは実行したプレイヤーの位置を参照するため、コマンドの実行はプレイヤー側から行ってください。<br>


##config.yml 設定内容
###gameconfig.gametime:  [Integer]
ゲームの時間を設定している<br>
単位は分

###gameconfig.startlocation.[team].[x,y,z]
ゲームの開始時、終了時のテレポート先を設定している。<br>
チーム名には[red/blue/spawn]がある。

###ruleconfig.[GameRule]: [boolean]
ゲーム用の隠しルールの有効化を設定している。


